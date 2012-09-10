function renderNavBar() {
    $.get('tl/navBar.tl', function(data) {
        var compiled = dust.compile(data, "navBarTemplate");
        dust.loadSource(compiled);
        var page = window.location.pathname;
        var lastIndex = page.lastIndexOf('/');
        page = page.substr(lastIndex + 1);
        
        var pointIndex = page.lastIndexOf('.');
        if (pointIndex != -1) {
          page = page.substr(0, pointIndex); 
        }

        var templateData = {};
        templateData[page] = "true";
        dust.render("navBarTemplate", templateData, function(err, out) {
          $('#navBar').html(out);
        });
    });
}