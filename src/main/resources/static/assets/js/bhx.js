function bxh(category) {
    $.ajax({
        method: "GET",
        url: "/bxh?category="+ category,
        data: {
            name: "John",
            location: "Boston"
        }
    })
        .done(function( msg ) {
            alert( "Data Saved: " + msg );
        });
}