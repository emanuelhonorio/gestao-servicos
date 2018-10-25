var Ujob = Ujob || {};

Ujob.MascaraCpfCnpf = (function(){

    function MascaraCpfCnpf(){
        this.radioTipoPessoa = $('.js-radio-tipo-pessoa');
        this.labelCpfCnpj = $('label[for=cpfOuCnpj]');
        this.inputCpfCnpj = $('#cpfOuCnpj');
    }

    MascaraCpfCnpf.prototype.iniciar = function(){
        this.radioTipoPessoa.on('change', onTipoPessoaAlterado.bind(this));
        var tipoPessoaSelecionada = this.radioTipoPessoa.filter(':checked')[0];
        if(tipoPessoaSelecionada){
            aplicarMascara.call(this, $(tipoPessoaSelecionada));
        }
    }

    function onTipoPessoaAlterado(evento){
        var tipoPessoaSelecionada = $(evento.currentTarget);
        aplicarMascara.call(this, tipoPessoaSelecionada);
        this.inputCpfCnpj.val('');
    }

    function aplicarMascara(tipoPessoaSelecionada){
        this.labelCpfCnpj.text(tipoPessoaSelecionada.data('documento'));
        this.inputCpfCnpj.removeAttr('disabled');
        this.inputCpfCnpj.mask(tipoPessoaSelecionada.data('mascara'));
    }

    return MascaraCpfCnpf;

})();

$(function(){

    var mascaraCpfCnpj = new Ujob.MascaraCpfCnpf();
    mascaraCpfCnpj.iniciar();

});