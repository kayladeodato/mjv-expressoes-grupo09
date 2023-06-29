public class ImpressorCupom {
    public void imprimir(Cupom cupom){
        /**
         vamos somente impimir o resultado no console
         mas este mesmo conteúdo poderá proporicionar várias saidas
         logo a variável conteudo deveria ser retornada para outros "processadores"
         como gerador de arquivo txt, pdf ou até mesmo envio por e-mail
         */

        /*
        CONSIDERE O COMPRIMENTO MÁXIMO DE 50 CARACTERES EM CADA LINHA
        E APLIQUE O RESPECTIVO ALINHAMENTO
         */
        StringBuilder conteudo = new StringBuilder();
        conteudo.append(tracos());
        conteudo.append(String.format("%-50s \n", cupom.nomeFantasia));
        Endereco end = cupom.endereco;
        conteudo.append(String.format("%s  N. %s  %s  %s \n%s - %s\n", end.logradouro, end.numero, end.complemento, end.bairro, end.cidade, end.uf));
        Integer espaco = 29 - cupom.cpf.length();
        conteudo.append(String.format("CPF/CNPJ:%s %-" + espaco + "s %s\n", cupom.cpf, "",cupom.data));
        conteudo.append(String.format("IE: %s %33tT\n", cupom.ie, cupom.hora));
        conteudo.append(String.format("IM: %-34s CCF: %d\n", cupom.im, cupom.ccf));
        conteudo.append(String.format("%38s CDD: %d\n","", cupom.cdd));
        conteudo.append(tracos());
        conteudo.append("CUPOM FISCAL\n");

        conteudo.append(String.format("ITEM COD. %-30s%10s\n","DESCRICAO","VALOR"));
        for(CupomItem item:cupom.itens){

            conteudo.append(String.format("%03d %4d  %-15.15s %.3f%s X %5.2f %8.2f\n", item.ordem, item.sku, item.descricao, item.quantidade, item.und, item.preco, item.subtotal));
        }
        conteudo.append(tracos());
        System.out.println(conteudo.toString());
        //em caso de resolver explorar algumas formas de apresentação
        //return conteudo.toString();
    }
    private String tracos(){
        String repeated = new String(new char[50]).replace("\0", "-");
        return repeated + "\n";
    }
    private String cpfCnpj(String cpfCnpj){
        String newCnpj = "";
        if(cpfCnpj.length()==11)
            newCnpj = cpfCnpj.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
        else
            newCnpj = cpfCnpj.replaceAll("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
        return newCnpj;
    }
}


