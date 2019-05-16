$(document).ready(function() {
    if($("#shedCheckbox").is(':checked')) {
        $("#shedToggleWidth").removeClass("shedToggle");
        $("#shedToggleLength").removeClass("shedToggle");
    }
    if($("#angleToggle").is(':checked')) {
        $("#angleToggleNumber").prop('disabled', false);
    }
    
    $("#shedCheckbox").change(function() {
        $("#shedToggleWidth").toggle("shedToggle");
        $("#shedToggleLength").toggle("shedToggle");
    });
    
    $("#angleToggle").change(function() {
        if($("#angleToggle").is(':checked')) {
            $("#angleToggleNumber").prop('disabled', false);
        } else {
            $("#angleToggleNumber").prop('disabled', true);
        }
    });
});


