async function getIdDisponivelId() {
    try {
        const response = await fetch('https://reqres.in/api/users?page=2')
        const dados = await response.json()
        const apiUsers = dados.data || [];
        const apiID = apiUsers.length ? Math.max(...apiUsers.map(user => user.id)) : 0

        const localUsers = []

        for (let i = 0; i < localStorage.length; i++) {
            let chave = localStorage.key(i)

            if (chave.startsWith('user_') && !chave.startsWith('del_user')) {
                let user = JSON.parse(localStorage.getItem(chave))
                localUsers.push(user)
            }
        }
        const localId = localUsers.length ? Math.max(...localUsers.map(user => user.id)) : 0
        const proximoId = Math.max(apiID, localId) + 1
        console.log(proximoId)
        return proximoId
    } catch {
        exibirModal(2, 'erro')
        return 1
    }
}

class User {
    constructor(id, nome, sobrenome, email, tipo) {
        this.id = id
        this.nome = nome
        this.sobrenome = sobrenome
        this.email = email
        this.tipo = tipo
    }

    validarDados() {
        for (let i in this) {
            if (this[i] == null || this[i] == '' || this[i] == undefined) {
                console.log(this[i])
                return false
            }
        }
        return true
    }
}

class Registro {
    constructor() {
        let id = getIdDisponivel()
        if (id === null) {
            localStorage.setItem('id', 0)
        }
    }
    //retorna todas as tarefas cadastradas
    receberTarefas() {
        let tarefas = []
        let id = localStorage.getItem('id')
        for (let i = 1; i <= id; i++) {
            let tarefa = JSON.parse(localStorage.getItem(i))
            if (tarefa === null) {
                continue
            }
            tarefa.id = i
            tarefas.push(tarefa)
        }
        return tarefas
    }

    procurar(tarefa) {
        let filtroTarefas = this.receberTarefas()
        filtroTarefas = filtroTarefas.filter(t => t.nome === nomeUsuario)
        filtroTarefas
        if (tarefa.descricao != '') {
            filtroTarefas = filtroTarefas.filter(t => t.descricao == tarefa.descricao)
        } if (tarefa.data != '') {
            filtroTarefas = filtroTarefas.filter(t => t.data == tarefa.data)
        } if (tarefa.prioridade != '') {
            filtroTarefas = filtroTarefas.filter(t => t.prioridade == tarefa.prioridade)
        } if (tarefa.status != '') {
            filtroTarefas = filtroTarefas.filter(t => t.status == tarefa.status)
        }
        return filtroTarefas
    }

    remover(id) {
        localStorage.removeItem(id)
    }

}

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
                getIdDisponivelId()
                break;
            case 4:
                chamarApi()
                break;
        }
        funcao = 0
    }
}

async function adicionarUsuario() {
    let id = await getIdDisponivelId()
    console.log(document.getElementById('nome').value)
    console.log(document.getElementById('email').value)
    let receberNome = document.getElementById('nome').value
    let quebrarNome = receberNome.split(" ")
    let nome = quebrarNome[0]
    let sobrenome = quebrarNome.slice(1).join(" ")
    let emailUsuario = document.getElementById('email').value
    let tipo = 1
    let user = new User(id, nome, sobrenome, emailUsuario, tipo)

    //post fake para simular a adição na api fake
    try {
        if(user.validarDados()) {
        const response = await fetch('https://reqres.in/api/users', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify({
                id: id,
                first_name: nome,
                last_name: sobrenome,
                email: emailUsuario
            })
        })
        console.log(response)

        if (!response.ok) {
            throw new Error('erro de merda')
        }

        const users = await response.json();

        localStorage.setItem(`user_${user.id}`, JSON.stringify(user))
        localStorage.setItem('id', id)
        exibirModal(1, "sucesso")
        limparCaixas()
    } else {
        exibirModal(2, "erro")
        limparCaixas()
    }

    } catch {
        console.error(error)
    }
}
function limparCaixas() {
    document.getElementById('nome').value = ''
    document.getElementById('id').value = ''
    document.getElementById('email').value = ''
}



function exibirModal(valor, tipo) {
    document.getElementById('estilo-modal').classList.add('text')
    document.getElementById('botao-config').innerHTML = 'Voltar'
    document.getElementById('botao-config').classList.add('btn')
    if (tipo == 'erro') {
        document.getElementById('titulo-modal').innerHTML = 'Erro!'
        document.getElementById('conteudo-modal').innerHTML = valor == 1 ? 'erro!' : 'Preencha todos os campos!'
        return $('#exibirModal').modal('show')
    }
    document.getElementById('titulo-modal').innerHTML = 'Sucesso!'
    document.getElementById('conteudo-modal').innerHTML = valor == 1 ? 'Usuário cadastrado com sucesso!' : 'Sua tarefa foi excluída com sucesso!'
    
    return $('#exibirModal').modal('show')

}
