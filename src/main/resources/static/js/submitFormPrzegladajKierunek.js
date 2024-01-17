function submitForm(kierunekId) {
    var formId = 'form_' + kierunekId;
    var form = document.getElementById(formId);
    form.action = '/gosc/przegladajkierunek/' + kierunekId ;
    form.submit();
}
