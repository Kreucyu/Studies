//cria as variáveis de altura e largura

var altura = 0
var largura = 0
var vidas = 1
var tempo = 15
var criaMosquitoTempo = 1500


//ajusta dificuldade do jogo
var nivel = window.location.search
nivel = nivel.replace('?', '')

if (nivel === 'normal') {
    var criaMosquitoTempo = 1500
} else if (nivel === 'dificil') {
    var criaMosquitoTempo = 1000
} else if (nivel === 'muito_dificil') {
    var criaMosquitoTempo = 750
}

function ajustaTamanhoPalcoJogo() {

    altura = window.innerHeight
    largura = window.innerWidth

    //Faz o programa saber qual o tamanho da tela para a aparição dos msoquitos

}

ajustaTamanhoPalcoJogo()


//tempo de jogo
var cronometro = setInterval(function() {

    tempo -= 1
    if (tempo < 0) {
        clearInterval(cronometro)
        clearInterval(criaMosca)
        window.location.href = 'vitoria.html'
    } else {
        document.getElementById('cronometro').innerHTML = tempo
    }

}, 1000)

function posicaoRandomica() {

    //remover mosquito caso exista
    if (document.getElementById('mosquito')) {
        document.getElementById('mosquito').remove()

        //gerenciamento de vidas
        if (vidas > 3) {
            window.location.href = 'fim_de_jogo.html'
        } else {
            document.getElementById('v' + vidas).src = "imagens/coracao_vazio.png"
            vidas++
        }
    }

    /*gera a posição dos mosquitos de fomra randomica, 
    subtraindo 90 para evitar ela ultrapasse o limite 
    da tela e também arredonda o valor para baixo*/

    var posicaoX = Math.floor(Math.random() * largura) - 90
    var posicaoY = Math.floor(Math.random() * altura) - 90

    //verifica se o valor for menor que 0 para evitar que o mosquito suma

    posicaoX = posicaoX < 0 ? 0 : posicaoX
    posicaoY = posicaoY < 0 ? 0 : posicaoY

    console.log(posicaoX, posicaoY)

    /*puxa a imagem dos mosquitos e 
    posiciona ela aleatoriamente de 
    acordo com as posições geradas*/

    var mosquito = document.createElement('img')
    mosquito.src = 'imagens/mosquito.png'
    mosquito.className = tamanhoAleatorio() + " " + ladoAleatorio()
    mosquito.style.left = posicaoX + 'px'
    mosquito.style.top = posicaoY + 'px'
    mosquito.style.position = 'absolute'
    mosquito.id = 'mosquito'
    mosquito.onclick = function () {
        this.remove()
    }
    document.body.appendChild(mosquito)
}

function tamanhoAleatorio() {
    //aletoriza o tamanho dos mosquitos com as classes criadas
    var classe = Math.floor(Math.random() * 3)
    if (classe == 0) {
        return 'mosquito1'
    } else if (classe == 1) {
        return 'mosquito2'
    } else {
        return 'mosquito3'
    }
}

function ladoAleatorio() {
    //aletoriza a direção dos mosquitos com as classes criadas
    var classe = Math.floor(Math.random() * 2)
    if (classe == 0) {
        return 'ladoA'
    } else {
        return 'ladoB'
    }
}


