var takna,h,m,s;

function Bulan(value) {
    switch (value) {
        case 0:
            return "January";
        case 1:
            return "February";
        case 2:
            return "March";
        case 3:
            return "April";
        case 4:
            return "May";
        case 5:
            return "June";
        case 6:
            return "July";
        case 7:
            return "August";
        case 8:
            return "September";
        case 9:
            return "October";
        case 10:
            return "November";
        case 11:
            return "December";
    }
}

function startTime() {
    try {
        takna = new Date();
        h = takna.getHours();
        m = takna.getMinutes();
        s = takna.getSeconds();
        // add a zero in front of numbers<10
        m=checkTime(m);
        s=checkTime(s);
        //document.getElementById('txt').innerHTML=h+":"+m+":"+s;
        document.getElementById("xiong").innerHTML = Bulan(takna.getMonth()) + " " + takna.getDate() + ", " + takna.getFullYear() + " " + h+":"+m+":"+s;
        //alert(document.getElementById("test").value);
        t=setTimeout('startTime()', 500);
    } catch (ex) {
        alert(ex);
    }
}

function checkTime(i) {
    if (i<10) {
        i="0" + i;
    }
    return i;
}

/*function addCommas(nStr) {
    nStr += '';
    x = nStr.split('.');
    x1 = x[0];
    x2 = x.length > 1 ? '.' + x[1] : '';
    var rgx = /(\d+)(\d{3})/;
    while (rgx.test(x1)) {

        x1 = x1.replace(rgx, '$1' + ',' + '$2');

    }
    return x1 + x2;
}*/