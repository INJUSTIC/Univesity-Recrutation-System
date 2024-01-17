function submitForm(kierunekId) {
    var cenaInputId = 'cenaZaWniosek_' + kierunekId;
    var formId = 'form_' + kierunekId;
    var cena = document.getElementById(cenaInputId).value;
    var form = document.getElementById(formId);
    form.action = '/DzialIT/zmienOplate/' + kierunekId + '/nowaCena/' + cena;
    form.submit();
}
