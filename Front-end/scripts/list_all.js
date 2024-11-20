function clearLoading(){
    document.getElementById("loading").style.display = "none";
}

function showAlimentos(alimentos){
    let tab = ` <thead>
        <th>id</th>
        <th>Nome</th>
        <th>Categoria</th>
        <th>Qtd Estoque</th>
        <th>Preço</th>
        <th>Data de Validade</th>
        <th>Data de Fabricação</th>
        <th>Peso por Unidade</th>
        <th>Marca</th>
        <th>País de Origem</th>
        <th>Código de Barras</th>
    </thead>`;

    for(let alimento of alimentos){
        tab += `
            <tr>
                <td>${alimento.id}</td>
                <td>${alimento.nome}</td>
                <td>${alimento.categoria}</td>
                <td>${alimento.qtd_estoque}</td>
                <td>${alimento.preco}</td>
                <td>${alimento.data_validade}</td>
                <td>${alimento.data_fabricacao}</td>
                <td>${alimento.peso_por_unidade}</td>
                <td>${alimento.marca}</td>
                <td>${alimento.pais_origem}</td>
                <td>${alimento.codigo_barras}</td>
                <td><img onclick="editar_alimento(${alimento.id})" src="images/edit01.png" width="20" height="15"></td>
                <td><img onclick="remove_alimento(${alimento.id})" src="images/trash01.png" width="20" height="15"></td>
            </tr>
        `;
    }
    document.getElementById("alimentos").innerHTML = tab;
}

async function listAllAlimentos(){
    const url = "http://localhost:8080/alimento/listall";
    const dados = await fetch(url, {method: "GET"});
    if(dados.status === 200){
        const alimentos = await dados.json();
        if(alimentos){
            clearLoading();
        }
        showAlimentos(alimentos);
    }
}

async function remove_alimento(id) {

    const resp = confirm('Deseja realmente remover o alimento?');

    if (!resp){
        return;
    }

    const url = `http://localhost:8080/alimento/delete/${id}`
    const result = await fetch(url, {method: "DELETE"});

    //Atualizando a página para mostrar a exclusão
    if (result.status === 204){
        listAllAlimentos();
    }
    else{
        alert('Erro ao remover o alimento');
    }
}

async function editar_alimento(id) {

    //Url para atualizar os dados
    const url = 'http://localhost:8080/alimento/update';
    const url_id = `http://localhost:8080/alimento/list/${id}`

    //Função para consistir os inputs
    const getInput = (campo, valor_atual) => {
        const valor = prompt(`${campo}: `, valor_atual);
        if (valor == null){
            alert('Operação Cancelada!');
            return null;
        }

        return valor || valor_atual;    
    }

    // Buscando os dados atuais do alimento
    const alimentoResponse = await fetch(url_id, {method: 'GET'});
    if (!alimentoResponse.ok) {
        alert('Erro ao buscar alimento');
        return;
    }

    //Convertendo os dados do alimento para json
    const alimento = await alimentoResponse.json();

    //Pegando os novos dados
    const nome = getInput('Nome', alimento.nome);
    if (nome === null) return;

    const categoria = getInput('Categoria', alimento.categoria);
    if (categoria === null) return;

    const qtd_estoque = getInput('Qtd Estoque', alimento.qtd_estoque);
    if (qtd_estoque === null) return;

    const preco = prompt('Preço: ', alimento.preco);
    if (preco === null) return;

    const data_validade = prompt('Data de Validade: ', alimento.data_validade);
    if (data_validade === null) return;

    const data_fabricacao = prompt('Data de Fabricação: ', alimento.data_fabricacao);
    if (data_fabricacao === null) return;

    const peso_por_unidade = prompt('Peso por Unidade: ', alimento.peso_por_unidade);
    if (peso_por_unidade === null) return;

    const marca = prompt('Marca: ', alimento.marca);
    if (marca === null) return;

    const pais_origem = prompt('País de Origem: ', alimento.pais_origem);
    if (pais_origem === null) return;

    const codigo_barras = prompt('Código de Barras: ', alimento.codigo_barras);
    if (codigo_barras === null) return;

    //Criando um objeto com todos os dados
    const dados = {
        id: id,
        nome: nome,
        categoria: categoria,
        qtd_estoque: qtd_estoque,
        preco: preco,
        data_validade: data_validade,
        data_fabricacao: data_fabricacao,
        peso_por_unidade: peso_por_unidade, 
        marca: marca,
        pais_origem: pais_origem,
        codigo_barras: codigo_barras,
    }

    //Tranformando os dados em um json
    const options = {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(dados)
    }

    //Atualizando os dados
    const result = await fetch(url, options);

    //Atualizando a página caso os dados tenham sido atualizados
    if (result.status == 200){
        listAllAlimentos();
    }
    else{
        alert('Não foi possível atualiza o alimento');
    } 
}

listAllAlimentos();


