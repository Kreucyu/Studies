async function getIdDisponivel() {
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
        console.log("proximo id" + proximoId)
        return proximoId
    } catch {
        exibirModal(2, 'erro')
        return 1
    }
}

class User {
    constructor(id, nome, sobrenome, email) {
        this.id = id
        this.nome = nome
        this.sobrenome = sobrenome
        this.email = email
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


    if (value == 'Adicionar usuário') { nomeAcesso.disabled = false, idAcesso.disabled = true, emailAcesso.disabled = false, funcao = 1 }
    if (value == 'Alterar usuário') { nomeAcesso.disabled = false, idAcesso.disabled = false, emailAcesso.disabled = false, funcao = 2 }
    if (value == 'Deletar usuário' || value == 'Consultar usuário') { nomeAcesso.disabled = true, idAcesso.disabled = false, emailAcesso.disabled = true, value == 'Deletar usuário' ? funcao = 4 : funcao = 3 }
    if (value == 'O que deseja fazer?') { nomeAcesso.disabled = true, idAcesso.disabled = true, emailAcesso.disabled = true }

    if (confirm) {
        switch (funcao) {
            case 1:
                funcoes(1)
                break;
            case 2:
                funcoes(2)
                break;
            case 3:
                getIdDisponivel()
                break;
            case 4:
                deletarUsuario()
                break;
        }
        funcao = 0
    }
}

function receberEntrada() {

}

async function funcoes(result) {
    let receberNome = document.getElementById('nome').value
    let quebrarNome = receberNome.split(" ")
    let nome = quebrarNome[0]
    let sobrenome = quebrarNome.slice(1).join(" ") || "N/A"
    let emailUsuario = document.getElementById('email').value

    result == 1 ? adicionarUsuario() : alterarUsuario(nome, sobrenome, emailUsuario)

    async function adicionarUsuario() {
        let id = await getIdDisponivel()
        let user = new User(id, nome, sobrenome, emailUsuario)
        try {
            if (user.validarDados()) {
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
                    throw new Error('Erro na API')
                }

                const users = await response.json();

                localStorage.setItem(`user_${user.id}`, JSON.stringify(user))
                localStorage.setItem('id', id)
                exibirModal(1, "sucesso")
                limparCaixas()
            } else {
                exibirModal(1, "erro")
                limparCaixas()
            }
        } catch {
            console.error(error)
        }
    }

    async function alterarUsuario(nome, sobrenome, emailUsuario) {
        let id = document.getElementById('id').value
        verificarId(id)
        if (id != "" && id != null && id != undefined) {
            atualizarDados = {"id": id}
            if (nome) atualizarDados.first_name = nome
            if (sobrenome) atualizarDados.last_name = sobrenome
            if (emailUsuario) atualizarDados.email = emailUsuario

            try {
                const response = await fetch('https://reqres.in/api/users/{id}', {
                    method: 'PATCH',
                    headers: {
                        'Content-type': 'application/json'
                    },
                    body: JSON.stringify(atualizarDados)
                })
                console.log(response)

                if (!response.ok) {
                    throw new Error('Erro na API')
                }

                const atualizacao = await response.json();
                if(Object.keys(atualizarDados).length > 0) {
                    localStorage.setItem(`user_${id}`, JSON.stringify(atualizarDados))
                    exibirModal(2, "sucesso")
                
                } else {
                    exibirModal(3, "sucesso")
                }
                limparCaixas()
            } catch {
                console.error(error)
            }
        } else {
            exibirModal(2, "erro")
            limparCaixas()
        }
    }

}

function deletarUsuario() {
    let id = document.getElementById('id').value
    verificarId(id)
    console.log('deletado')
}

async function verificarId(id) {
    function localVerify(id) {
        const usuario = localStorage.getItem(`user_${id}`)
        return usuario ? true : false
    }
    async function apiVerify(id) {
        try {
            const response = await fetch(`https://reqres.in/api/users/${id}`)
            if (response.status === 404) {
                return false
            } else {
                return true;
            }
        } catch {
            console.error(error)
            return false
        }
    }

    if (localVerify(id)) {
        console.log(`trilhou no local`)
    }
    if (await apiVerify(id)) {
        console.log(`trilhou na api`)
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
        document.getElementById('conteudo-modal').innerHTML = valor == 1 ? 'Preencha todos os campos!' : valor == 2 ? 'Insira um ID para alterar' : 'ID não encontrado'
        return $('#exibirModal').modal('show')
    }
    document.getElementById('titulo-modal').innerHTML = 'Sucesso!'
    document.getElementById('conteudo-modal').innerHTML = valor == 1 ? 'Usuário cadastrado com sucesso!' : valor == 2 ? 'Alteração concluída!' : 'Nenhuma alteração foi feita!'

    return $('#exibirModal').modal('show')

}
