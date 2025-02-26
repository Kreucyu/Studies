/* Projeto: Gerenciador de imagens
Aluno: Elcio Cleiton Wippel
Conteúdo: inclui todos os métodos responsáveis por fazer com que o sistema funcione
e seja interativo, utilizando a manipulação do DOM na captura dos dados, fazendo a chamada
da API utilizando Fetch e seus metodos HTTP e demais funções do JavaScript para a manipulação
de caminhos do código para receber o resultado esperado*/

/* getIdDisponivel() - função que serve para calcular qual o próximo id disponível,
é recebido todas as fotos da api e são filtradas por id
a quantidade das fotos são armazenadas em uma variável e em seguida, feito 
a verificação dos ids dentro do localStorage e depois, é feita a junção das duas
somas para assim somar +1 e retornar o próximo id */

async function getIdDisponivel() {
    try {
        const response = await fetch('https://jsonplaceholder.typicode.com/photos')
        const photos = await response.json()
        const apiPhotos = photos || [];
        const apiID = apiPhotos.length ? Math.max(...apiPhotos.map(photo => photo.id)) : 0

        const localPhotos = []

        for (let i = 0; i < localStorage.length; i++) {
            let chave = localStorage.key(i)

            if ((chave.startsWith('photo_') || chave.startsWith('altL_photo_')) && !chave.startsWith('del_photo')) {
                let photo = JSON.parse(localStorage.getItem(chave))
                localPhotos.push(photo)
            }
        }
        const localId = localPhotos.length ? Math.max(...localPhotos.map(photo => photo.id)) : 0
        const proximoId = Math.max(apiID, localId) + 1
        console.log("proximo id " + proximoId)
        return proximoId
    } catch {
        exibirModal(3, 'erro')
        return 1
    }
}

/* getAlbumId() - função que serve para calcular qual o próximo albumId disponível,
é recebido todos os albuns da api e são filtradas por albumId
a quantidade dos albuns são armazenados em uma variável e em seguida, feito 
a verificação dos ids dentro do localStorage e, é feita a soma do primeiro valor inserido
no album e do ultimo, para verificar se o resultado sera 49, se for 49, podera seguir para o proximo
albumid, se for removido ira cair de album id e se for a primeira adição, irá iniciar no album 101, para
dar continuidade aos dados da api */

async function getAlbumId() {
    try {
        const response = await fetch('https://jsonplaceholder.typicode.com/photos')
        const photos = await response.json()
        const apiPhotos = photos || [];
        const apiAlbumID = apiPhotos.length ? Math.max(...apiPhotos.map(photo => photo.albumId)) : 0
        const localPhotos = []

        for (let i = 0; i < localStorage.length; i++) {
            let chave = localStorage.key(i)

            if ((chave.startsWith('photo_') || chave.startsWith('altL_photo_')) && !chave.startsWith('del_photo')) {
                let photo = JSON.parse(localStorage.getItem(chave))
                localPhotos.push(photo)
            }
        }
        const localAlbumId = localPhotos.length ? Math.max(...localPhotos.map(photo => photo.albumId)) : 0
        let ultimoAlbumId = Math.max(apiAlbumID, localAlbumId)
        if (ultimoAlbumId === 100) ultimoAlbumId = 101
        let quantidadeAlbumId = localPhotos.filter(photo => photo.albumId === ultimoAlbumId)
        console.log(ultimoAlbumId)
        if (quantidadeAlbumId.length > 0) {
            let ordenar = quantidadeAlbumId.map(photo => photo.id).sort((a, b) => a - b)
            let primeiroValor = ordenar[0]
            let ultimoValor = ordenar[ordenar.length - 1]
            let total = ordenar.length
            let ocupados = ultimoValor - primeiroValor
            if (ocupados === 49 || total === 50) ultimoAlbumId++
        }

        localStorage.setItem(`albumId`, ultimoAlbumId)
        console.log("proximo album id " + ultimoAlbumId)
        return ultimoAlbumId
    } catch {
        exibirModal(3, 'erro')
        return 1
    }
}

/* confirmarDelete(value) - função que serve para manipular os eventos do clique
do modal, exclusivo para exclusão, após a confirmação da ação de delete os eventos
são removidos para evitar bugs */

function confirmarDelete(value) {
    return new Promise((resolve, reject) => {
        const confirmacao = document.getElementById('botao-delete')
        const confirmacaoHandler = () => {
            limparBotoes()
            resolve(true)
        }
        const voltar = document.getElementById('botao-config')
        const voltarHandler = () => {
            limparBotoes()
            resolve(false)
        }
        confirmacao.addEventListener('click', confirmacaoHandler)
        voltar.addEventListener('click', voltarHandler)

        function limparBotoes(params) {
            confirmacao.removeEventListener('click', confirmacaoHandler)
            voltar.removeEventListener('click', voltarHandler)
        }
    })
}

/* Objeto photo, utilizado para a adição de imagens, sendo instanciado um objeto para
que seja possível fazer a adição de imagens de forma mais simples, com um método que verifica
se os valores não são vazios. */

class Photo {
    constructor(id, albumId, title, url, thumbnailUrl) {
        this.id = id
        this.albumId = albumId
        this.title = title
        this.url = url
        this.thumbnailUrl = thumbnailUrl
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

let funcao = 0 //variável de definição de método

/*limitar(value, confirm) - função que utiliza o DOM para fazer a manipulação dos dados
e dos formulários da página index.html, é passado para um método um valor e uma confirmação,
o valor ira definir quais caixas serão habilitadas para preenchimento e qual função será executada
o confirm irá retornar true somente quando for solicitado a confirmação da função, e assim ira adaptadar
e limpar a exibição como necessário  */

function limitar(value, confirm) {
    document.getElementById('drop').innerHTML = value
    let titleAcesso = document.getElementById('title')
    let urlAcesso = document.getElementById('url')
    let idAlbumAcesso = document.getElementById('albumId')
    let thumbAcesso = document.getElementById('thumbnailUrl')
    let idAcesso = document.getElementById('id')
    let list = document.getElementById('imageList')
    if (value == 'Adicionar imagem') { thumbAcesso.disabled = false, idAcesso.disabled = true, urlAcesso.disabled = false, titleAcesso.disabled = false, idAlbumAcesso.disabled = true, list.style.display = "none", funcao = 1, limparCaixas() }
    if (value == 'Alterar imagem') { thumbAcesso.disabled = false, idAcesso.disabled = false, urlAcesso.disabled = false, titleAcesso.disabled = false, idAlbumAcesso.disabled = true, list.style.display = "none", funcao = 2, limparCaixas() }
    if (value == 'Consultar imagem') { thumbAcesso.disabled = true, idAcesso.disabled = false, urlAcesso.disabled = true, titleAcesso.disabled = true, idAlbumAcesso.disabled = false, list.style.display = "block", funcao = 3, consultarImagem(), limparCaixas() }
    if (value == 'Deletar imagem') { thumbAcesso.disabled = true, idAcesso.disabled = false, urlAcesso.disabled = true, titleAcesso.disabled = true, idAlbumAcesso.disabled = true, list.style.display = "none", funcao = 4, limparCaixas() }
    if (value == 'O que deseja fazer?') { thumbAcesso.disabled = true, idAcesso.disabled = true, urlAcesso.disabled = true, titleAcesso.disabled = true, idAlbumAcesso.disabled = true, list.style.display = "none" }

    if (confirm) {
        switch (funcao) {
            case 1:
                funcoes(1)
                break;
            case 2:
                funcoes(2)
                break;
            case 3:
                list.style.display = "block"
                let paginas = document.getElementById('paginas')
                paginas.innerHTML = ''
                consultarImagem()
                break;
            case 4:
                deletarImagem()
                break;
        }
        funcao = 0
    }
}

/*funcoes(result) - função responsável por fazer a coleta dos dados para a futura chamada
da alteração ou adição de imagens, com base no comparador tenário que verifica qual valor
enviado pelo metodo limitar()  */

async function funcoes(result) {
    let title = document.getElementById('title').value
    let url = document.getElementById('url').value
    let thumbnailUrl = document.getElementById('thumbnailUrl').value
    let albumId = await getAlbumId()

    result == 1 ? await adicionarImagem() : await alterarImagem()

    /*adicionarImagem() - função responsável pela adição de imagens no localStorage e solcitação "fake"
    para a api pelo meio do método POST, passando para ele os dados recebidos pelo DOM e passando ao 
    localStorage os dados armazenados no objeto photo */

    async function adicionarImagem() {
        let id = await getIdDisponivel()
        let photo = new Photo(id, albumId, title, url, thumbnailUrl)
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
                        thumbnailUrl: thumbnailUrl
                    })
                })
                if (!response.ok) throw new Error('Erro na API')
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

    /*alterarImagem() - função responsável pela alteração dos dados de uma imagem, podendo ela estar
    na api, no local ou em ambos, garantindo que seja modificado APENAS os dados passados e não todos
    os dados já registrados anteriormente, começamos com uma validação de id, para saber se esse id
    informado existe, em seguida é verificado em qual meio o id existe, e se o valor informado para
    atualização for diferente de nulo, ele será adicionado, caso contrário será mantido o valor anterior,
    utilizei o método PATCH para fazer apenas as alteraçãoes necessárias */

    async function alterarImagem() {
        let id = document.getElementById('id').value
        if (id != "" && id != null && id != undefined) {
            let validarId = await verificarId(id)
            if (!validarId || Object.keys(validarId).length === 0) {
                limparCaixas()
                exibirModal(3, "erro")
                return
            } else {
                console.log(`idv  dentro ` + validarId)
                let atualizarDados = {}
                if (id) atualizarDados.id = parseInt(id)
                if (title) atualizarDados.title = title
                if (url) atualizarDados.url = url
                if (thumbnailUrl) atualizarDados.thumbnailUrl = thumbnailUrl

                if (validarId.localOnly) {
                    const photoKey = `photo_${id}`
                    const altLKey = `altL_photo_${id}`
                    const valorAtual = (localStorage.getItem(photoKey)) ? JSON.parse(localStorage.getItem(`photo_${id}`)) :
                        (localStorage.getItem(altLKey)) ? JSON.parse(localStorage.getItem(`altL_photo_${id}`)) : null
                    if (!valorAtual) {
                        console.error(`valor nao encontrado!`)
                        return
                    }
                    if (title && title !== valorAtual.title) valorAtual.title = title
                    if (url && url !== valorAtual.url) valorAtual.url = url
                    if (thumbnailUrl && thumbnailUrl !== valorAtual.thumbnailUrl) valorAtual.thumbnailUrl = thumbnailUrl
                    localStorage.removeItem(photoKey)
                    localStorage.setItem(altLKey, JSON.stringify(valorAtual))
                    limparCaixas()
                    exibirModal(2, "sucesso")
                } else if (validarId.foundInAll) {
                    const delKey = `del_photo_${id}`
                    if (localStorage.getItem(delKey)) return exibirModal(3, `erro`)
                    const valorAtual = JSON.parse(localStorage.getItem(`alt_photo_${id}`))
                    if (title && title !== valorAtual.title) valorAtual.title = title
                    if (url && url !== valorAtual.url) valorAtual.url = url
                    if (thumbnailUrl && thumbnailUrl !== valorAtual.thumbnailUrl) valorAtual.thumbnailUrl = thumbnailUrl
                    localStorage.setItem(`alt_photo_${id}`, JSON.stringify(valorAtual))
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

                        if (!response.ok) throw new Error('Erro na API')
                        const atualizacao = await response.json();
                        localStorage.setItem(`alt_photo_${id}`, JSON.stringify(atualizarDados))
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

/* consultarImagem() - função responsável pela exibição das imagens na api e no localStorage,
sua busca aceitando campos de albumId e Id, permitindo com que sejam visualizados tambem todos os
dados ou os dados filtrados, se baseia na variaavel paginaAtual e itensPorPagina para fazer a 
paginação adequada dos itens, o método verifica os itens da API, determinados por id, album, ou todos,
em seguida puxa os dados do localStorage, incluindo adições, alterações e deleções, e faz as devidas
mudanças no que deve ou não ser exibido e incrementa as novas imagens junto com as anteriores da API,
método utilizado foi o GET. */

let paginaAtual = 1
const itensPorPagina = 50

async function consultarImagem() {
    let id = document.getElementById('id').value
    let albumId = document.getElementById('albumId').value
    const inicio = (paginaAtual - 1) * itensPorPagina
    const fim = inicio + itensPorPagina

    if (id !== "" && albumId === "") {
        try {
            let validarId = await verificarId(id)
            if (!validarId) return exibirModal(4, "erro") && limparCaixas()
            let exibicao = null
            let alterador = `alt_photo_${id}`
            let localAdc = `photo_${id}`
            let localAdcAlt = `altL_photo_${id}`
            const response = await fetch(`https://jsonplaceholder.typicode.com/photos/${id}`)
            console.log(response)
            if (response.ok) exibicao = await response.json()
            if (!exibicao || Object.keys(exibicao).length === 0) {
                if (localStorage.getItem(localAdc)) exibicao = JSON.parse(localStorage.getItem(localAdc))
                else if (localStorage.getItem(localAdcAlt)) exibicao = JSON.parse(localStorage.getItem(localAdcAlt))
                else return exibirModal(4, "erro") && limparCaixas()
            }


            if (localStorage.getItem(alterador)) {
                exibicao = { ...exibicao, ...JSON.parse(localStorage.getItem(alterador)) }
            }
            let deletado = `del_photo_${exibicao.id}`
            if (localStorage.getItem(deletado)) {
                return exibirModal(4, "erro") && limparCaixas()
            }
            if (exibicao) exibirTabela([exibicao])
            else exibirTabela([])
        } catch (error) {
            console.error(error)

        }

    }

    else if (id === "" && albumId !== "") {
        try {
            let validarAlbumId = await verificarAlbumId(albumId)
            if (!validarAlbumId) return exibirModal(4, "erro") && limparCaixas()
            const response = await fetch(`https://jsonplaceholder.typicode.com/photos?albumId=${albumId}`)
            if (!response.ok) throw new Error('Erro na API')
            console.log(response)
            const data = await response.json()
            let exibicao = []
            exibicao = [...data]
            let localAdc = Object.keys(localStorage).filter(key => key.startsWith('photo_')).map(key => JSON.parse(localStorage.getItem(key))).filter(imagem => imagem.albumId === Number(albumId))
            localAdc.push(...Object.keys(localStorage).filter(key => key.startsWith('altL_photo_')).map(key => JSON.parse(localStorage.getItem(key))).filter(imagem => imagem.albumId === Number(albumId)))
            localAdc.sort((a, b) => a.id - b.id)
            exibicao = [...data, ...localAdc]
            exibicao = exibicao.map(imagem => {
                if (!imagem || !imagem.id) return imagem
                let alterador = `alt_photo_${imagem.id}`
                if (localStorage.getItem(alterador)) return { ...imagem, ...JSON.parse(localStorage.getItem(alterador)) }
                return imagem
            })
            let exclusoes = Object.keys(localStorage).filter(key => key.startsWith('del_photo_')).map(key => key.replace('del_photo_', ''))
            exibicao = exibicao.filter(imagem => !exclusoes.includes(String(imagem.id)))
            const imagensPaginadas = exibicao.slice(inicio, fim)
            if (exibicao.length === 0) return exibirModal(4, "erro") && limparCaixas()
            exibirTabela(imagensPaginadas)
        } catch (error) {
            console.error(error)
        }
    }

    else {
        try {
            const response = await fetch(`https://jsonplaceholder.typicode.com/photos/`)
            console.log(response)
            if (!response.ok) throw new Error('Erro na API')
            const data = await response.json()
            let exibicao = []
            exibicao = [...data]
            let localAdc = Object.keys(localStorage).filter(key => key.startsWith('photo_')).map(key => JSON.parse(localStorage.getItem(key)))
            localAdc.push(...Object.keys(localStorage).filter(key => key.startsWith('altL_photo_')).map(key => JSON.parse(localStorage.getItem(key))))
            localAdc.sort((a, b) => a.id - b.id)
            exibicao = [...data, ...localAdc]
            exibicao = exibicao.map(imagem => {
                let alterador = `alt_photo_${imagem.id}`
                if (localStorage.getItem(alterador)) return { ...imagem, ...JSON.parse(localStorage.getItem(alterador)) }
                return imagem
            })
            let exclusoes = Object.keys(localStorage).filter(key => key.startsWith('del_photo_')).map(key => key.replace('del_photo_', ''))
            exibicao = exibicao.filter(imagem => !exclusoes.includes(String(imagem.id)))
            const imagensPaginadas = exibicao.slice(inicio, fim)
            exibirTabela(imagensPaginadas)
            paginacao(exibicao.length)
        } catch (error) {
            console.error(error)
        }
    }
    limparCaixas()

    /* paginacao(paginas) - função que recebe a quantidade de páginas e faz a divisão entre a quantidade e os
    itens por cada pagina, fazendo assim com que a exibição aconteça de maneira correta e com a devida quantidade 
    de páginas */

    function paginacao(paginas) {
        const totalPaginas = Math.ceil(paginas / itensPorPagina)
        let paginasDiv = document.getElementById(`paginas`)
        paginasDiv.innerHTML = ``

        if (paginaAtual > 1) {
            let botaoVoltar = document.createElement(`button`)
            botaoVoltar.innerHTML = '<i class="fa-solid fa-angle-left"></i>'
            botaoVoltar.className = "arrows"
            botaoVoltar.onclick = () => {
                paginaAtual--
                consultarImagem()
            }
            paginasDiv.appendChild(botaoVoltar)
        }

        let pAtual = document.createElement('span')
        pAtual.textContent = `Página ${paginaAtual} de ${totalPaginas}`
        paginasDiv.appendChild(pAtual)

        if (paginaAtual < totalPaginas) {
            let botaoAvancar = document.createElement(`button`)
            botaoAvancar.innerHTML = '<i class="fa-solid fa-angle-right"></i>'
            botaoAvancar.className = "arrows"
            botaoAvancar.onclick = () => {
                paginaAtual++
                consultarImagem()
            }
            paginasDiv.appendChild(botaoAvancar)
        }

    }

    /* exibirTabela(data) - função que recebe os dados solicitados anteriormente com a filtragem 
    e trata as informações para serem exibidas dentro de uma tabela selecionada pelo DOM da index.html
    fazendo assim as determinadas alterações visuais para que possam ser exibidas na tela do usuário */

    function exibirTabela(data) {
        if (!Array.isArray(data)) {
            console.error("os dados recebidos não estão em um array")
            return
        }
        let imagensLista = document.getElementById('imagensLista')
        imagensLista.innerHTML = ''

        data.forEach(i => {
            let inserirLinha = imagensLista.insertRow()
            let cell0 = inserirLinha.insertCell(0)
            let cell1 = inserirLinha.insertCell(1)
            let cell2 = inserirLinha.insertCell(2)
            let cell3 = inserirLinha.insertCell(3)
            let cell4 = inserirLinha.insertCell(4)
            cell0.innerHTML = i.id
            cell0.style.fontWeight = "bolder"
            cell1.innerHTML = i.title
            cell1.style.paddingRight = "60px"
            cell1.style.paddingLeft = "60px"
            cell2.innerHTML = `<a href="${i.url}">${i.url}</a>`
            cell3.innerHTML = `<a href="${i.thumbnailUrl}">${i.thumbnailUrl}</a>`
            cell4.innerHTML = i.albumId
            cell4.style.fontWeight = "bolder"
        })
    }
}

/* deletarImagem() - método responsável por deletar uma imagem com o determinado ID que foi
inserido pelo usuário, caso o ID não seja válido uma mensagem de erro é exibida, foi utilizado
o método DELETE para fazer a requisisão para a API e claro, é feita a separação de onde foi 
encontrado o ID, para assim verificar como o método deverá prosseguir, excluindo do local, da api
ou de ambos. */

async function deletarImagem() {
    let id = document.getElementById('id').value
    if (id != "" && id != null && id != undefined) {
        let validarId = await verificarId(id)
        console.log(validarId)
        if (!validarId) {
            limparCaixas()
            exibirModal(3, "erro")
            return
        } else {
            if (validarId.localOnly) {
                exibirModal(1, 'delete')
                const confirmacao = await confirmarDelete()
                if (confirmacao) {
                    const photoKey = `photo_${id}`
                    const altLKey = `altL_photo_${id}`
                    localStorage.removeItem(photoKey)
                    localStorage.removeItem(altLKey)
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

                        if (!response.ok) throw new Error('Erro na API')
                        if (validarId.foundInAll) localStorage.removeItem(`alt_photo_${id}`)
                        if (validarId.foundInAll) localStorage.removeItem(`photo_${id}`)
                        localStorage.setItem(`del_photo_${id}`, JSON.stringify(parseInt(id)))
                        limparCaixas()
                        exibirModal(3, 'sucesso')

                    } catch (error) {
                        console.error(error)
                    }
                }
            } else {
                limparCaixas()
                exibirModal(3, "erro")
                return
            }
        }
    }
}

/* verificarId(id) - função utilizada práticamente no código inteiro, responsável por validar
o id informado pelo usuário, faz a verificação no localStorage (localVerify(id)) e na API (apiVerify(id))
cada método retorna sua devida resposta caso o id seja encontrado ou não, com essa resposta é realizado
o tratamento do que será retornado, e dependendo da resposta retornada os métodos anteriores serão alterados
por ela, utilizado método GET {id}. */

async function verificarId(id) {
    function localVerify(id) {
        const photo = localStorage.getItem(`photo_${id}`)
        const altPhoto = localStorage.getItem(`alt_photo_${id}`)
        const altLPhoto = localStorage.getItem(`altL_photo_${id}`)
        const delPhoto = localStorage.getItem(`del_photo_${id}`)
        return photo || altPhoto || altLPhoto || delPhoto ? true : false
    }
    async function apiVerify(id) {
        try {
            const response = await fetch(`https://jsonplaceholder.typicode.com/photos/${id}`)
            if (!response.ok) return false
            const data = await response.json()
            return data && Object.keys(data).length > 0 ? true : false
        } catch {
            console.error(error)
            return false
        }
    }

    const localConfirm = localVerify(id)
    const apiConfirm = await apiVerify(id)

    if (!apiConfirm && !localConfirm) {
        return null
    }

    const resposta = {
        localOnly: localConfirm && !apiConfirm,
        apiOnly: !localConfirm && apiConfirm,
        foundInAll: localConfirm && apiConfirm,
    }

    return resposta
}

/* verificarAlbumId(albumId) - função com a mesma finalidade do método anterior, retornar um ID, mas desta vez sendo
o albumId, nesse caso na verificação local o método entra diretamente nos objetos para assim receber o seu valor,
filtrando e comparando qual almbumId for encontrado, na api a verificação é feita pelo GET acessando diretamente os albuns
já registrados, após a chamada desses métodos é enviada a resposta para os métodos anteriores fazer as devidas manipulações. */

async function verificarAlbumId(albumId) {
    function localVerify(albumId) {
        for (let i = 0; i < localStorage.length; i++) {
            const key = localStorage.key(i)

            if (key.startsWith('photo_') || key.startsWith('altL_photo_')) {
                let valor = localStorage.getItem(key)

                if (!valor) {
                    continue
                }
                try {
                    let dados = JSON.parse(valor)
                    return dados && dados.albumId && String(dados.albumId) === String(albumId) ? true : false
                } catch (error) {
                    console.error(error)
                    continue
                }
            }
        }
        return false
    }
    async function apiVerify(albumId) {
        try {
            const response = await fetch(`https://jsonplaceholder.typicode.com/photos?albumId=${albumId}`)
            if (!response.ok) return false
            const data = await response.json()
            return Array.isArray(data) && data.length > 0 ? true : false
        } catch {
            console.error(error)
            return false
        }
    }

    const localConfirm = localVerify(albumId)
    const apiConfirm = await apiVerify(albumId)

    const resposta = {
        localOnly: localConfirm && !apiConfirm,
        apiOnly: !localConfirm && apiConfirm,
        foundInAll: localConfirm && apiConfirm,
    }
    return resposta
}

/* consultarCond(escolha) - função auxiliar, usada somente para bloquear a possibilidade de receber um albumId 
e um Id ao mesmo tempo no método consultar, garantindo que seja possível somente o envio de 1 deles. */

function consultarConf(escolha) {
    let idAlbumAcesso = document.getElementById('albumId')
    let idAcesso = document.getElementById('id')
    if (escolha == 0 && funcao == 3) {
        idAlbumAcesso.disabled = false
        idAcesso.disabled = true
    } else if (escolha == 1 && funcao == 3) {
        idAlbumAcesso.disabled = true
        idAcesso.disabled = false
    }
    if (idAlbumAcesso.value == '' && idAcesso.value == '' && funcao == 3) {
        idAlbumAcesso.disabled = false
        idAcesso.disabled = false
    }
}

/* limparCaixas() - função chamada durante o código inteiro, responsável por sempre limpar todas as caixas
de valores do formulário após algum erro ou sucesso dos métodos, garantindo que sempre seja possível digitar
novos dados sem a necessidade de apaga-los todas as vezes */

function limparCaixas() {
    document.getElementById('title').value = ''
    document.getElementById('url').value = ''
    document.getElementById('thumbnailUrl').value = ''
    document.getElementById('id').value = ''
    document.getElementById('albumId').value = ''
}

/* exibibrModal(valor, tipo) - função chamada durante o código inteiro, responsável pela chamada do modal,
criado na página index.html e manipulado via DOM (manipulação de texto, cores e estilos no geral) 
para que seja feita sempre a exibição correta, podendo ser uma mensagem de erro, sucesso e confirmação, 
o valor armazena qual a mensagem que será exibida e o tipo armazena qual é o tipo desse modal, 
a chamada do modal e feita utilizando Jquerry */

function exibirModal(valor, tipo) {
    document.getElementById('estilo-modal').classList.add('text')
    document.getElementById('botao-config').innerHTML = 'Voltar'
    document.getElementById('botao-delete').style.display = "none"
    if (tipo == 'erro') {
        document.getElementById('title-modal').innerHTML = 'Erro!'
        document.getElementById('conteudo-modal').innerHTML = valor == 1 ? 'Preencha todos os campos!' : valor == 2 ? 'Insira um ID para alterar' : valor == 3 ? 'ID não encontrado' : 'Não foi encontrado nenhum dado!'
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


/*
  Projeto: Gerenciador de Imagens
  Autor: Elcio Cleiton Wippel
  Descrição: Documentação dos métodos responsáveis pelo funcionamento do sistema.
  O sistema interage com a API utilizando Fetch e métodos HTTP, manipulando o DOM
  para capturar e exibir dados dinamicamente.
*/

/* 
  getIdDisponivel() 
  - Retorna o próximo ID disponível para uma nova imagem. 
  - Obtém todas as fotos da API, filtra por ID e considera os registros no localStorage.
  - A soma das quantidades é feita para determinar o próximo ID disponível. 
*/

/* 
  getAlbumId() 
  - Retorna o próximo albumId disponível. 
  - Obtém todos os álbuns da API e verifica os IDs armazenados no localStorage.
  - Se a soma do primeiro e último ID do álbum resultar em 49, um novo albumId é gerado.
  - Se um álbum for removido, o ID é reatribuído. A primeira adição inicia no albumId 101. 
*/

/* 
  confirmarDelete(value) 
  - Gerencia os eventos de clique no modal de exclusão.
  - Após a confirmação da exclusão, remove os eventos para evitar bugs. 
*/

/* 
  Objeto photo
  - Representa uma imagem adicionada ao sistema.
  - Inclui métodos para validar os valores antes da inserção.
*/

/* 
  limitar(value, confirm) 
  - Manipula elementos do DOM e formulários da página index.html.
  - Define quais campos serão habilitados e qual função será executada.
  - O parâmetro confirm retorna true quando a função exige confirmação, ajustando a exibição.
*/

/* 
  funcoes(result) 
  - Coleta dados para futuras operações de adição ou alteração de imagens.
  - Usa um operador ternário para determinar qual ação executar com base no método limitar().
*/

/* 
  adicionarImagem() 
  - Adiciona uma nova imagem ao localStorage e faz uma solicitação POST "fake" à API.
  - Os dados são capturados do DOM e armazenados no objeto photo.
*/

/* 
  alterarImagem() 
  - Modifica os dados de uma imagem na API, localStorage ou ambos.
  - Apenas os campos alterados são modificados, preservando os dados originais.
  - Valida se o ID existe antes de atualizar. Utiliza o método PATCH.
*/

/* 
  consultarImagem() 
  - Exibe imagens da API e do localStorage.
  - Aceita buscas por albumId ou Id, ou retorna todos os registros.
  - Implementa paginação com base na variável paginaAtual e itensPorPagina.
  - Faz merge entre os dados da API e localStorage, considerando adições, alterações e deleções.
  - Utiliza o método GET.
*/

/* 
  paginacao(paginas) 
  - Divide os registros conforme a quantidade de itens por página.
  - Controla a exibição correta das imagens e mantém a estrutura de navegação. 
*/

/* 
  exibirTabela(data) 
  - Recebe os dados filtrados e os exibe em uma tabela no DOM.
  - Atualiza os elementos visuais para apresentação ao usuário.
*/

/* 
  deletarImagem() 
  - Remove uma imagem pelo ID informado.
  - Se o ID for inválido, exibe uma mensagem de erro.
  - Utiliza o método DELETE para remover da API e/ou localStorage.
*/

/* 
  verificarId(id) 
  - Valida se um ID informado pelo usuário existe na API ou no localStorage.
  - Utiliza localVerify(id) e apiVerify(id) para determinar a resposta correta.
  - O resultado influencia as operações subsequentes.
  - Utiliza o método GET {id}.
*/

/* 
  verificarAlbumId(albumId) 
  - Similar a verificarId(), mas verifica a existência de um albumId.
  - Filtra os objetos localmente e consulta a API para buscar registros.
  - O resultado é usado para ajustar as funções que manipulam álbuns. 
*/

/* 
  consultarCond(escolha) 
  - Função auxiliar que impede o envio simultâneo de um albumId e um Id em consultas.
  - Garante que apenas um parâmetro seja utilizado por vez. 
*/

/* 
  limparCaixas() 
  - Reseta os campos do formulário após a execução de uma função.
  - Evita que o usuário tenha que apagar os dados manualmente a cada operação. 
*/

/* 
  exibirModal(valor, tipo) 
  - Exibe mensagens modais para erro, sucesso ou confirmação.
  - Manipula a interface via DOM para alterar texto, cores e estilos.
  - Implementado com jQuery.
*/
