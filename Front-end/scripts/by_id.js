
function mostrarAlimento(alimento){

    document.querySelector('#vazio').innerHTML = "";

    document.querySelector('#nome').innerHTML = '<strong>Nome</strong>: ' + alimento.nome;

    document.querySelector('#categoria').innerHTML = '<strong>Categoria</strong>: ' + alimento.categoria;

    document.querySelector('#qtd_estoque').innerHTML = '<strong>Qtd Estoque</strong>: ' + alimento.qtd_estoque;

    document.querySelector('#data_validade').innerHTML = '<strong>Data de Validade</strong>: ' + alimento.data_validade;

    document.querySelector('#data_fabricacao').innerHTML = '<strong>Data de Fabricação</strong>: ' + alimento.data_fabricacao;

    document.querySelector('#peso_por_unidade').innerHTML = '<strong>Peso por Unidade</strong>: ' + alimento.peso_por_unidade;

    document.querySelector('#marca').innerHTML = '<strong>Marca</strong>: ' + alimento.marca;

    document.querySelector('#pais_origem').innerHTML = '<strong>País de Origem</strong>: ' + alimento.pais_origem;

    document.querySelector('#codigo_barras').innerHTML = '<strong>Código de Barras</strong>: ' + alimento.codigo_barras;
}

function clear(){
    document.querySelector('#vazio').innerHTML = "(Sem Resultados)";
    document.querySelector('#nome').innerHTML = "";
    document.querySelector('#categoria').innerHTML = "";
    document.querySelector('#qtd_estoque').innerHTML = "";
    document.querySelector('#preco').innerHTML = "";
    document.querySelector('#data_validade').innerHTML = "";
    document.querySelector('#data_fabricacao').innerHTML = "";
    document.querySelector('#peso_por_unidade').innerHTML = "";
    document.querySelector('#marca').innerHTML = "";
    document.querySelector('#pais_origem').innerHTML = "";
    document.querySelector('#codigo_barras').innerHTML = "";
}

async function consultarPorId(event){

    event.preventDefault();

    const idAlimento = document.querySelector("#idAlimento").value;

    //Verificando se o id do alimento está dentro do range
    if (idAlimento.length < 1){
        alert('ID Inválido');
        return;
    }

    //Conectando com o servidor para mostrar os dados
    try{

        const dados = await fetch(`http://localhost:8080/alimento/list/${idAlimento}`);

        if (dados.status == 200){
            const alimento = await dados.json();
            mostrarAlimento(alimento);

        }
        else{
            alert('ID: ' + idAlimento + ' não encontrado')
            clear()
        }

    }catch(erro){
        alert("Não foi possível conectar com o backend")
    }
}