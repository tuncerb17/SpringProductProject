$(function () {
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

function remove(url,formId){
    if (confirm("Bu işlemi gerçekleştirmek istediğine emin misin?")) {
        $.ajax({
            type: "DELETE",
            url: url,
            data: $(formId).serialize(), // serializes the form's elements.
            success: function(data)
            {
                var response = JSON.parse(data);
                alert(response.message);
                location.reload();
            }
        });
    }
}