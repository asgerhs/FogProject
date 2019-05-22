$(document).ready(function() {
    $("#ajaxForm").submit(function (e) {
        e.preventDefault();
        ajax($(this));
    });
    
    
});

// Ajax Command
function ajax(formObj) {
    $.ajax({
        url: $(formObj).find('button').attr('formaction'),
        data: $(formObj).serialize()
    }).done(function (data, textStatus, request) {
        if (request.getResponseHeader('error') !== null) {
            $("#errorBox #message").html(request.getResponseHeader('message'));
            $("#errorBox").show();
            $("#successBox").hide();
        } else {
            $("#successBox").prepend(request.getResponseHeader('message'));
            $("#successBox").show();
            $("#errorBox").hide();
        }
    });
}
