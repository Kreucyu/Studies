//função que decide quais campos poderão ser preenchidos
let funcao = 0

function limitar(value, confirm) {
    document.getElementById('drop').innerHTML = value
    let nomeAcesso = document.getElementById('nome')
    let idAcesso = document.getElementById('id')
    let salarioAcesso = document.getElementById('salario')
    let idadeAcesso = document.getElementById('idade')
    

    if (value == 'Adicionar funcionário' || value == 'Alterar funcionário') {
        nomeAcesso.disabled = false;
        idAcesso.disabled = true;
        salarioAcesso.disabled = false;
        idadeAcesso.disabled = false;
        value == 'Adicionar funcionário' ? funcao = 1 : funcao = 2
    } if (value == 'Deletar funcionário' || value == 'Consultar funcionário') {
        nomeAcesso.disabled = true;
        idAcesso.disabled = false;
        salarioAcesso.disabled = true;
        idadeAcesso.disabled = true;
        value == 'Deletar funcionário' ? funcao = 3 : funcao = 4
    } if (value == 'O que deseja fazer?') {
        nomeAcesso.disabled = true;
        idAcesso.disabled = true;
        salarioAcesso.disabled = true;
        idadeAcesso.disabled = true;
    }
    if(confirm) {
    switch (funcao) {
        case 1:
            alert('adc')
            break;
        case 2:
            alert('alt')
            break;
        case 3:
            alert('del')
            break;
        case 4:
            chamarApi()
            break;
    }
    funcao = 0
}
}

async function chamarApi() {
    const response = await fetch('https://reqres.in/api/users?page=1')
    const post = await response.json();
    console.log(post)
}
