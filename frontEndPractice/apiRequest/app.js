//função que decide quais campos poderão ser preenchidos
let funcao = 0

function limitar(value, confirm) {
    document.getElementById('drop').innerHTML = value
    let nomeAcesso = document.getElementById('nome')
    let idAcesso = document.getElementById('id')
    let emailAcesso = document.getElementById('email')


    if (value == 'Adicionar usuário' || value == 'Alterar usuário') {
        nomeAcesso.disabled = false;
        idAcesso.disabled = true;
        emailAcesso.disabled = false;
        value == 'Adicionar usuário' ? funcao = 1 : funcao = 2
    } if (value == 'Deletar usuário' || value == 'Consultar usuário') {
        nomeAcesso.disabled = true;
        idAcesso.disabled = false;
        emailAcesso.disabled = true;
        value == 'Deletar usuário' ? funcao = 3 : funcao = 4
    } if (value == 'O que deseja fazer?') {
        nomeAcesso.disabled = true;
        idAcesso.disabled = true;
        emailAcesso.disabled = true;
    }
    if (confirm) {
        switch (funcao) {
            case 1:
                adicionarUsuario()
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
        document.getElementById('nome').value = ''
        document.getElementById('id').value = ''
        document.getElementById('email').value = ''
    }
}

async function chamarApi() {
    const response = await fetch('https://reqres.in/api/users?page=2')
    const users = await response.json();
    console.log(users)
}

async function adicionarUsuario() {
    let receberNome = document.getElementById('nome').value
    let quebrarNome = receberNome.split(" ")
    let nome = quebrarNome[0]
    let sobrenome = quebrarNome.slice(1).join(" ")
    let emailUsuario = document.getElementById('email').value
    const response = await fetch('https://reqres.in/api/users', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify({
            id: proximoId(),
            first_name: nome,
            last_name: sobrenome,
            email: emailUsuario
        })
    })
    const users = await response.json();
    console.log(users)
}

function proximoId() {
    return 15
}

function exibirModal(valor, tipo) {
    if (tipo == 'erro') {
        document.getElementById('titulo-modal').innerHTML = 'Erro!'
        document.getElementById('conteudo-modal').innerHTML = valor == 1 ? 'Insira um nome de usuário!' : 'Preencha todos os campos!'
        document.getElementById('estilo-modal').classList.add('text')
        document.getElementById('botao-config').innerHTML = 'Voltar'
        document.getElementById('botao-config').classList.add('btn')
        return $('#exibirModal').modal('show')
    }
    document.getElementById('titulo-modal').innerHTML = 'Sucesso!'
    document.getElementById('conteudo-modal').innerHTML = valor == 1 ? 'Sua tarefa foi adicionada com sucesso!' : 'Sua tarefa foi excluída com sucesso!'
    document.getElementById('estilo-modal').classList.add('text')
    document.getElementById('botao-config').innerHTML = 'Voltar'
    document.getElementById('botao-config').classList.add('btn')
    return $('#exibirModal').modal('show')

}
