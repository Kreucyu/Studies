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
    return 150
}

//ALTERAR AQUI

function confirmarDelete(value) {
    return new Promise((resolve, reject) => {
        const confirmacao = document.getElementById('botao-delete')
        confirmacao.addEventListener('click', () => {
            resolve(true)
        })
        const voltar = document.getElementById('botao-config')
        voltar.addEventListener('click', () => {
            resolve(false)
        })
        return () => {
            confirmacao.removeEventListener('click', confirmacaoHandler)
            voltar.removeEventListener('click', voltarHandler)
        }
    })
}

class Photo {
    constructor(id, albumId, title, url, thumbUrl) {
        this.id = id
        this.albumId = albumId
        this.title = title
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

let funcao = 0

function limitar(value, confirm) {
    document.getElementById('drop').innerHTML = value
    let titleAcesso = document.getElementById('title')
    let urlAcesso = document.getElementById('url')
    let idAlbumAcesso = document.getElementById('albumID')
    let thumbAcesso = document.getElementById('thumbUrl')
    let idAcesso = document.getElementById('id')



    if (value == 'Adicionar imagem') { thumbAcesso.disabled = false, idAcesso.disabled = true, urlAcesso.disabled = false, titleAcesso.disabled = false, idAlbumAcesso.disabled = true, funcao = 1 }
    if (value == 'Alterar imagem') { thumbAcesso.disabled = false, idAcesso.disabled = false, urlAcesso.disabled = false, titleAcesso.disabled = false, idAlbumAcesso.disabled = true, funcao = 2 }
    if (value == 'Deletar imagem' || value == 'Consultar imagem') { thumbAcesso.disabled = true, idAcesso.disabled = false, urlAcesso.disabled = true, titleAcesso.disabled = true, idAlbumAcesso.disabled = true, value == 'Deletar imagem' ? funcao = 4 : funcao = 3 }
    if (value == 'O que deseja fazer?') { thumbAcesso.disabled = true, idAcesso.disabled = true, urlAcesso.disabled = true, titleAcesso.disabled = true, idAlbumAcesso.disabled = true }

    if (confirm) {
        switch (funcao) {
            case 1:
                funcoes(1)
                break;
            case 2:
                funcoes(2)
                break;
            case 3:

                break;
            case 4:
                deletarImagem()
                break;
        }
        funcao = 0
    }
}

async function funcoes(result) {
    let title = document.getElementById('title').value
    let url = document.getElementById('url').value
    let thumbUrl = document.getElementById('thumbUrl').value
    let albumId = await getAlbumId()

    result == 1 ? await adicionarImagem() : await alterarImagem()

    async function adicionarImagem() {
        let id = await getIdDisponivel()
        let photo = new Photo(id, albumId, title, url, thumbUrl)
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
        } catch (error) {
            console.error(error)
        }

    }

    async function alterarImagem() {
        let id = document.getElementById('id').value
        if (id != "" && id != null && id != undefined) {
            let validarId = await verificarId(id)
            if (!validarId) {
                limparCaixas()
                exibirModal(3, "erro")
                return
            } else {

                let atualizarDados = {}
                if (id) atualizarDados.id = parseInt(id)
                if (title) atualizarDados.title = title
                if (url) atualizarDados.url = url
                if (thumbUrl) atualizarDados.thumbnailUrl = thumbUrl

                if (validarId.localOnly || validarId.foundInAll) {
                    const valorAtual = JSON.parse(localStorage.getItem(`photo_${id}`))
                    if (title && title !== valorAtual.title) valorAtual.title = title
                    if (url && url !== valorAtual.url) valorAtual.url = url
                    if (thumbUrl && thumbUrl !== valorAtual.thumbUrl) valorAtual.thumbUrl = thumbUrl
                    localStorage.setItem(`photo_${id}`, JSON.stringify(valorAtual))
                    limparCaixas()
                    exibirModal(2, "sucesso")
                } else if (validarId.apiOnly) {
                    try {
                        const response = await fetch(`https://jsonplaceholder.typicode.com/photos/${id}`, {
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
                        localStorage.setItem(`photo_${id}`, JSON.stringify(atualizarDados))
                        limparCaixas()
                        exibirModal(2, "sucesso")
                    } catch (error) {
                        console.error(error)
                    }
                }
            }
        } else {
            limparCaixas()
            exibirModal(2, "erro")
            return
        }
    }
}

async function deletarImagem() {
    let id = document.getElementById('id').value
    if (id != "" && id != null && id != undefined) {
        let validarId = await verificarId(id)
        if (!validarId) {
            limparCaixas()
            exibirModal(3, "erro")
            return
        } else {
            if (validarId.localOnly) {
                exibirModal(1, 'delete')
                const confirmacao = await confirmarDelete()
                if (confirmacao) {
                    localStorage.removeItem(`photo_${id}`)
                    limparCaixas()
                    setTimeout(() => {
                        exibirModal(3, 'sucesso')
                      }, "1000");   
                }
            } if (validarId.foundInAll || validarId.apiOnly) {
                exibirModal(1, 'delete')
                const confirmacao = await confirmarDelete()
                if (confirmacao) {
                    try {
                        const response = await fetch(`https://jsonplaceholder.typicode.com/photos/${id}`, {
                            method: 'DELETE',
                            headers: {
                                'Content-type': 'application/json'
                            }
                        })
                        console.log(response)

                        if (!response.ok) {
                            throw new Error('Erro na API')
                        }
                        if (validarId.foundInAll) {
                            localStorage.removeItem(`photo_${id}`)
                        }
                        limparCaixas()
                        exibirModal(3, 'sucesso')

                    } catch (error) {
                        console.error(error)
                    }
                }
            }
        }
    }
}



    async function verificarId(id) {
        function localVerify(id) {
            const usuario = localStorage.getItem(`photo_${id}`)
            return usuario ? true : false
        }
        async function apiVerify(id) {
            try {
                const response = await fetch(`https://jsonplaceholder.typicode.com/photos/${id}`)
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

        const localConfirm = localVerify(id)
        const apiConfirm = await apiVerify(id)

        const resposta = {
            localOnly: localConfirm && !apiConfirm,
            apiOnly: !localConfirm && apiConfirm,
            foundInAll: localConfirm && apiConfirm,
        }

        return resposta


    }

    function limparCaixas() {
        document.getElementById('title').value = ''
        document.getElementById('url').value = ''
        document.getElementById('thumbUrl').value = ''
        document.getElementById('id').value = ''
    }

    function exibirModal(valor, tipo) {
        document.getElementById('estilo-modal').classList.add('text')
        document.getElementById('botao-config').innerHTML = 'Voltar'
        document.getElementById('botao-delete').style.display = "none"
        if (tipo == 'erro') {
            document.getElementById('title-modal').innerHTML = 'Erro!'
            document.getElementById('conteudo-modal').innerHTML = valor == 1 ? 'Preencha todos os campos!' : valor == 2 ? 'Insira um ID para alterar' : 'ID não encontrado'
            return $('#exibirModal').modal('show')
        } if (tipo == 'delete') {
            document.getElementById('title-modal').innerHTML = 'Deletar'
            document.getElementById('conteudo-modal').innerHTML = 'Prosseguir com a exclusão?'
            document.getElementById('botao-delete').style.display = "inline"
            document.getElementById('botao-delete').innerHTML = 'Confirmar'
            return $('#exibirModal').modal('show')
        }
        document.getElementById('title-modal').innerHTML = 'Sucesso!'
        document.getElementById('conteudo-modal').innerHTML = valor == 1 ? 'Imagem adicionada com sucesso!' : valor == 2 ? 'Alteração concluída!' : 'Imagem excluída com sucesso!'
        return $('#exibirModal').modal('show')

    }
