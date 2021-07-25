
var SEGMENT_LENGTH = 3;

var wavesurfer = WaveSurfer.create({
    container: '#waveform',
    waveColor: 'gray',
    progressColor: 'green',
    backend: 'MediaElement',
    mediaControls: 'true',
    height: '100'
});
wavesurfer.load('http://localhost:8000/output.wav'); // TODO: Change this value to the full unsegmented file
//wavesurfer.load('http://ia902606.us.archive.org/35/items/shortpoetry_047_librivox/song_cjrg_teasdale_64kb.mp3');

var time;

wavesurfer.on('audioprocess', function () {
    if (wavesurfer.getCurrentTime() % SEGMENT_LENGTH > 0 && wavesurfer.getCurrentTime() % SEGMENT_LENGTH < 1)
    {
        console.log(wavesurfer.getCurrentTime());
        time = wavesurfer.getCurrentTime();
        document.getElementById('result').innerHTML = wavesurfer.getCurrentTime();
    }
    
});

function retrieveTime()
{
    return time;
}

