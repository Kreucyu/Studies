async function getIdDisponivel() {
    try {
        const response = await fetch('https://jsonplaceholder.typicode.com/photos')
        const photos = await response.json()
        const apiPhotos = photos || [];
        const apiID = apiPhotos.length ? Math.max(...apiPhotos.map(photo => photo.id)) : 0

        const localPhotos = []

        for (let i = 0; i < localStorage.length; i++) {
            let chave = localStorage.key(i)

            if (chave.startsWith('photo_') && !chave.startsWith('del_photo')) {
                let photo = JSON.parse(localStorage.getItem(chave))
                localPhotos.push(photo)
            }
        }
        const localId = localPhotos.length ? Math.max(...localPhotos.map(photo => photo.id)) : 0
        const proximoId = Math.max(apiID, localId) + 1
        console.log("proximo id" + proximoId)
        return proximoId
    } catch {
        exibirModal(3, 'erro')
        return 1
    }
}

async function getAlbumId() {
    try {
        const response = await fetch('https://jsonplaceholder.typicode.com/photos')
        const photos = await response.json()
        const apiPhotos = photos || [];
        const apiAlbumId = apiPhotos.length ? Math.max(...apiPhotos.map(photo => photo.albumId)) : 0
        const localPhotos = []

        for (let i = 0; i < localStorage.length; i++) {
            let chave = localStorage.key(i)

            if (chave.startsWith('photo_') && !chave.startsWith('del_photo')) {
                let photo = JSON.parse(localStorage.getItem(chave))
                localPhotos.push(photo)
            }
        }
        const localAlbumId = localPhotos.length ? Math.max(...localPhotos.map(photo => photo.id)) : 0
        const proximoAlbumId = Math.max(apiAlbumId, localAlbumId) + 1
        console.log("proximo album id" + proximoAlbumId)
        return proximoAlbumId
    } catch {
        exibirModal(3, 'erro')
        return 1
    }
}

class Photo {
    constructor(id, albumId, titulo, url, thumbUrl) {
        this.id = id
        this.albumId = albumId
        this.titulo = titulo
        this.url = url
        this.thumbUrl = thumbUrl
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

}

let funcao = 0

function limitar(value, confirm) {
    document.getElementById('drop').innerHTML = value
    let tituloAcesso = document.getElementById('titulo')
    let urlAcesso = document.getElementById('url')
    let idAlbumAcesso = document.getElementById('albumID')
    let thumbAcesso = document.getElementById('thumbUrl')
    let idAcesso = document.getElementById('id')    
    


    if (value == 'Adicionar imagem') 
        { thumbAcesso.disabled = false, idAcesso.disabled = true, urlAcesso.disabled = false, tituloAcesso.disabled = false, idAlbumAcesso.disabled = true, funcao = 1 }
    if (value == 'Alterar imagem') 
        { thumbAcesso.disabled = false, idAcesso.disabled = false, urlAcesso.disabled = false, tituloAcesso.disabled = false, idAlbumAcesso.disabled = true, funcao = 2 }
    if (value == 'Deletar imagem' || value == 'Consultar imagem') 
        { thumbAcesso.disabled = true, idAcesso.disabled = false, urlAcesso.disabled = true, tituloAcesso.disabled = true, idAlbumAcesso.disabled = true, value == 'Deletar imagem' ? funcao = 4 : funcao = 3 }
    if (value == 'O que deseja fazer?') 
        { thumbAcesso.disabled = true, idAcesso.disabled = true, urlAcesso.disabled = true, tituloAcesso.disabled = true, idAlbumAcesso.disabled = true }

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
                getAlbumId()
                break;
        }
        funcao = 0
    }
}

async function funcoes(result) {
    let titulo = document.getElementById('titulo').value
    let url = document.getElementById('url').value
    let thumbUrl = document.getElementById('thumbUrl').value

    result == 1 ? adicionarImagem() : alterarImagem()

    async function adicionarImagem() {
        let id = await getIdDisponivel()
        let albumId = await getAlbumId()
        let photo = new Photo(id, albumId, titulo, url, thumbUrl)
        console.log(photo)
        try {
            if (photo.validarDados()) {
                const response = await fetch('https://jsonplaceholder.typicode.com/photos', {
                    method: 'POST',
                    headers: {  
                        'Content-type': 'application/json'
                    },
                    body: JSON.stringify({
                        albumId: albumId,
                        id: id,
                        title: title,
                        url: url,
                        thumbnailUrl: thumbUrl
                    })
                })
                console.log(response)

                if (!response.ok) {
                    throw new Error('Erro na API')
                }

                const photos = await response.json();

                localStorage.setItem(`photo_${photo.id}`, JSON.stringify(photo))
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
                const response = await fetch('', {
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
            const response = await fetch(``)
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
    document.getElementById('conteudo-modal').innerHTML = valor == 1 ? 'Imagem adicionada com sucesso!' : valor == 2 ? 'Alteração concluída!' : 'Nenhuma alteração foi feita!'

    return $('#exibirModal').modal('show')

}
