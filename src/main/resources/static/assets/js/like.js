function likeBtn(id){
    $.ajax({
        method: "GET",
        url: "/like?id="+id,

    })
        .done(function( msg ) {
            alert( "Data Saved: " + msg );
        });
}
