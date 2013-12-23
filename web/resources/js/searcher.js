function attacher(div_id, html_file) {
    try {
        $('#'+div_id).load(html_file);
    } catch (ex) {
        alert(ex);
    }
}

function showTop(docOID) {
    var windowObjectReference = null; // global variable
    try {
        if(windowObjectReference == null || windowObjectReference.closed)
            windowObjectReference = window.open('docViewer.html?docOID='+docOID, 'Document Window', 'menubar=no,location=no,resizable=yes,scrollbars=yes,status=yes');
        else
            windowObjectReference.focus();
                    
    } catch(ex) {
        alert(ex);
    }
}
