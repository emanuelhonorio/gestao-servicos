$(function(){

    var profileImg = $("#profile");
    var inputNomeFoto = $("input[name=foto]");
    var inputContentType  = $("input[name=contentType]");

    var settings = {
        type: 'json',
        action: '/fotos',
        allow: '*.(jpg|jpeg|gif|png)',
        complete: function(resposta){
            var urlFoto = '/fotos/temp/' + resposta.foto;
            
            inputNomeFoto.val(resposta.foto);
            inputContentType.val(resposta.contentType);

            // console.log(inputNomeFoto);
            // console.log(inputContentType);
            // console.log(resposta);
            // console.log(resposta.foto);
            // console.log(resposta.contentType);
            profileImg.attr('src', urlFoto);
        }
    };

    UIkit.uploadSelect($("#upload"), settings),
    UIkit.uploadDrop(profileImg, settings);

     if($("input[name=foto]").val()){
        settings.complete({foto: inputNomeFoto.val(), contentType: inputContentType.val()});
    }
    
});