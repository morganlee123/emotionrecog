var wavesurfer = WaveSurfer.create({
    container: '#waveform',
    waveColor: 'violet',
    progressColor: 'purple',
    backend: 'MediaElement',
    mediaControls: 'true'
});
wavesurfer.load('./../../../../RealTimeER/tests/RAVDESS_samples/output.wav'); // TODO: Change this value to the full unsegmented file
