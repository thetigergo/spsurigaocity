function entries(div_id, html_file) {
    try {
        $('#'+div_id).load("save/"+html_file);
    } catch (ex) {
        alert(ex);
    }
}


