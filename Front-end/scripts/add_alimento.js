/*function clear_text(){
    document.querySelector('#descricao').value = "";
    document.querySelector('#preco').value = "";
    document.querySelector('#fornecedor').value = "";
    document.querySelector('#qtd_estoque').value = "";
}*/


async function addAlimento(event) {

    event.preventDefault();

    //Obtendo todos os dados do forms
    const form = document.querySelector('#formadd');

    //Verificando se todos os dados foram preenchidos
    if(!form.checkValidity()){
        alert('Preencha todos os campos do formulário');
        return;
    }

    const formData = new FormData(form);
    const alimento = Object.fromEntries(formData);
    const url = 'http://localhost:8080/alimento/add';

    //Transformando os dados em um json
    const options = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(alimento)
    }

    //Conectando com o servidor para pegar os dados
    try{

        const result = await fetch(url, options);

        if(result.status == 201){
            location.reload()
            alert('O alimento foi cadastrado com sucesso!');
            
        }
        else{
            alert('O alimento não foi cadastrado.');
        }

    }catch(erro){
        alert("Não foi possível conectar com o backend")
    }
}