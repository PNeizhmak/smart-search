var core = {};
core.getServiceUrl = function (baseUrl) {
    var platform = $('#smart-search-form select#Platform').val();
    var apiMethod = $('#smart-search-form select#Search-param').val();
    var value = $('#smart-search-form input#search-value').val();
    return baseUrl + platform + "/" + apiMethod + "/" + value;
};

$(function() {

    var baseUrl = "http://localhost:8081/"

    $('#smart-search-form').submit(function(e) {
        e.preventDefault();
        $.ajax({
            url: core.getServiceUrl(baseUrl),
            type: 'get',
            success: function(data) {
                var textarea = $('#smart-search-form textarea#result');
                textarea.val(JSON.stringify(data));
                console.log(data);
            }
        });
    });
});