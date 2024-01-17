function submitForm(wydzialId) {
    var formId = 'form_' + wydzialId;
    var form = document.getElementById(formId);
    form.action = '/gosc/kierunki/' + wydzialId ;
    form.submit();
}
