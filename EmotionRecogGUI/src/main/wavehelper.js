
var SEGMENT_LENGTH = 3;

var wavesurfer = WaveSurfer.create({
    container: '#waveform',
    waveColor: 'gray',
    progressColor: 'green',
    backend: 'MediaElement',
    mediaControls: 'true'
});
wavesurfer.load('./../../../../RealTimeER/tests/RAVDESS_samples/output.wav'); // TODO: Change this value to the full unsegmented file
//wavesurfer.load('http://ia902606.us.archive.org/35/items/shortpoetry_047_librivox/song_cjrg_teasdale_64kb.mp3');

wavesurfer.on('audioprocess', function () {
    if (wavesurfer.getCurrentTime() % SEGMENT_LENGTH > 0 && wavesurfer.getCurrentTime() % SEGMENT_LENGTH < 1)
    {
        console.log(wavesurfer.getCurrentTime());
        document.getElementById('result').innerHTML = wavesurfer.getCurrentTime();
    }
    
});

