#!/usr/bin/env python
# coding: utf-8

# # Real time emotion detection program

# In[33]:

#import os
#os.system('conda activate emotion-recog')


# In[34]:


import tensorflow.keras
import pandas as pd
import pyaudio
import librosa
from scipy.stats import zscore
import numpy as np
import matplotlib.pyplot as plt
from IPython.display import Audio


# ## Load our pre-trained emotion recognition model

# In[35]:


# TODO: Find a better way to pass this through the java program. This is just a temporary fix
model = tensorflow.keras.models.load_model('/Users/morgan/Work/MSU/IPROBE/research/EmotionRecogGUIDev/EmotionRecogGUI/src/main/[CNN-LSTM]M.h5')


# ## Open the microphone and input the stream to test data to feed the model

# In[36]:


import pyaudio
import wave
import sys

FORMAT = pyaudio.paInt16
CHANNELS = 1
RATE = 44100
CHUNK = 512
RECORD_SECONDS = 5
WAVE_OUTPUT_FILENAME = sys.argv[1]
device_index = 2
audio = pyaudio.PyAudio()

# Read audio file
sample_rate = 16000
max_pad_len = 49100


y, sr = librosa.core.load(WAVE_OUTPUT_FILENAME, sr=sample_rate, offset=0.5)

# Z-normalization
y = zscore(y)

# Padding or truncated signal
if len(y) < max_pad_len:
    y_padded = np.zeros(max_pad_len)
    y_padded[:len(y)] = y
    y = y_padded
elif len(y) > max_pad_len:
    y = np.asarray(y[:max_pad_len])

# Add to signal list
signal = y


# Number of augmented data
nb_augmented = 2

# Function to add noise to a signals with a desired Signal Noise ratio (SNR)
def noisy_signal(signal, snr_low=15, snr_high=30, nb_augmented=2):

    # Signal length
    signal_len = len(signal)

    # Generate White noise
    noise = np.random.normal(size=(nb_augmented, signal_len))

    # Compute signal and noise power
    s_power = np.sum((signal / (2.0 ** 15)) ** 2) / signal_len
    n_power = np.sum((noise / (2.0 ** 15)) ** 2, axis=1) / signal_len

    # Random SNR: Uniform [15, 30]
    snr = np.random.randint(snr_low, snr_high)

    # Compute K coeff for each noise
    K = np.sqrt((s_power / n_power) * 10 ** (- snr / 10))
    K = np.ones((signal_len, nb_augmented)) * K

    # Generate noisy signal
    return signal + K.T * noise



augmented_signal = noisy_signal(signal)


def mel_spectrogram(y, sr=16000, n_fft=512, win_length=256, hop_length=128, window='hamming', n_mels=128, fmax=4000):

    # Compute spectogram
    mel_spect = np.abs(librosa.stft(y, n_fft=n_fft, window=window, win_length=win_length, hop_length=hop_length)) ** 2

    # Compute mel spectrogram
    mel_spect = librosa.feature.melspectrogram(S=mel_spect, sr=sr, n_mels=n_mels, fmax=fmax)

    # Compute log-mel spectrogram
    mel_spect = librosa.power_to_db(mel_spect, ref=np.max)

    return mel_spect


# In[43]:


signal = [signal]
augmented_signal = [augmented_signal]

mel_spect = np.asarray(list(map(mel_spectrogram, signal)))
augmented_mel_spect = [np.asarray(list(map(mel_spectrogram, augmented_signal[i]))) for i in range(len(augmented_signal))]


# In[44]:


# Time distributed parameters
win_ts = 128
hop_ts = 64

# Split spectrogram into frames
def frame(x, win_step=128, win_size=64):
    nb_frames = 1 + int((x.shape[2] - win_size) / win_step)
    frames = np.zeros((x.shape[0], nb_frames, x.shape[1], win_size)).astype(np.float32)
    for t in range(nb_frames):
        frames[:,t,:,:] = np.copy(x[:,:,(t * win_step):(t * win_step + win_size)]).astype(np.float32)
    return frames
X_test = frame(mel_spect, hop_ts, win_ts)


# In[46]:


X_test = X_test.reshape(X_test.shape[0], X_test.shape[1] , X_test.shape[2], X_test.shape[3], 1)


# In[47]:


y_pred = model.predict(X_test)


# In[48]:


# Positioning of categorical to numerical
# 0 - Anger
# 1 - Disgust
# 2 - Fear
# 3 - Happy
# 4 - Neutral
# 5 - Sad
# 6 - Surprise

pred_string = ','.join(str(x) for x in y_pred[0])
print(pred_string)

"""
emotion = np.argmax(y_pred)
if emotion == 0:
    print('Angry')
elif emotion == 1:
    print('Disgust')
elif emotion == 2:
    print('Fear')
elif emotion == 3:
    print('Happy')
elif emotion == 4:
    print('Neutral')
elif emotion == 5:
    print('Sad')
elif emotion == 6:
    print('Surprise')
"""


# In[ ]:




