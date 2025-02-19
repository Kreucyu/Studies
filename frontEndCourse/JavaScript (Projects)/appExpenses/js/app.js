class Despesa {
    constructor(ano, mes, dia, tipo, descricao, valor) {
        this.ano = ano
        this.mes = mes
        this.dia = dia
        this.tipo = tipo
        this.descricao = descricao
        this.valor = valor
    }
    validarDados() {
        for (let i in this) {
            if (this[i] == undefined || this[i] == '' || this[i] == null) {
                return false
            }
        }
        return true
    }
}

class Bd {
    constructor() {
        let id = localStorage.getItem('id')
        if (id === null) {
            localStorage.setItem('id', 0)
        }
    }

    getProximoId() {
        let proximoId = localStorage.getItem('id')
        return parseInt(proximoId) + 1
    }

    gravar(d) {
        let id = this.getProximoId()
        localStorage.setItem(id, JSON.stringify(d))
        localStorage.setItem('id', id)
    }

    recuperarTodosRegistros() {
        let despesas = []
        let id = localStorage.getItem(`id`)
        for (let i = 1; i <= id; i++) {
            let despesa = JSON.parse(localStorage.getItem(i))
            if (despesa === null) {
                continue
            }
            despesa.id = i
            despesas.push(despesa)
        }
        return despesas
    }

    pesquisar(despesa) {
        let despesasFiltradas = []
        despesasFiltradas = this.recuperarTodosRegistros()
        if (despesa.ano != ``) {
            despesasFiltradas = despesasFiltradas.filter(d => d.ano == despesa.ano)
        } if (despesa.mes != ``) {
            despesasFiltradas = despesasFiltradas.filter(d => d.mes == despesa.mes)
        } if (despesa.dia != ``) {
            despesasFiltradas = despesasFiltradas.filter(d => d.dia == despesa.dia)
        } if (despesa.tipo != ``) {
            despesasFiltradas = despesasFiltradas.filter(d => d.tipo == despesa.tipo)
        } if (despesa.descricao != ``) {
            despesasFiltradas = despesasFiltradas.filter(d => d.descricao == despesa.descricao)
        } if (despesa.valor != ``) {
            despesasFiltradas = despesasFiltradas.filter(d => d.valor == despesa.valor)
        }
        return despesasFiltradas
    }

    remover (id) {
        localStorage.removeItem(id)
    }
}

let bd = new Bd()

function cadastrarDespesa() {

    let ano = document.getElementById('ano')
    let mes = document.getElementById('mes')
    let dia = document.getElementById('dia')
    let tipo = document.getElementById('tipo')
    let descricao = document.getElementById('descricao')
    let valor = document.getElementById('valor')

    let despesa = new Despesa(ano.value,
        mes.value, dia.value, tipo.value,
        descricao.value, valor.value)

    if (despesa.validarDados()) {
        bd.gravar(despesa)
        document.getElementById("exampleModalLabel").innerText = "Registro inserido com sucesso"

        let text = (document.getElementById("text")) //poderia apenas criar uma classe e nao remover a outra!
        text.classList.remove("text-danger")
        text.classList.add("text-success")

        document.getElementById("modal_conteudo").innerText = "Despesa foi cadastrada com sucesso!"
        document.getElementById("btn-text").innerText = "Voltar"

        let button = (document.getElementById("btn-text")) //poderia apenas criar uma classe e nao remover a outra!
        button.classList.remove("btn-danger")
        button.classList.add("btn-success")
        $('#registraDespesa').modal('show')

        ano.value = ''
        mes.value = ''
        dia.value = ''
        tipo.value = ''
        descricao.value = ''
        valor.value = ''

    } else {
        document.getElementById("exampleModalLabel").innerText = "Erro na gravação"

        let text = (document.getElementById("text")) //poderia apenas criar uma classe e nao remover a outra!
        text.classList.remove("text-success")
        text.classList.add("text-danger")

        document.getElementById("modal_conteudo").innerText = "Existem campos obrigatorios que não foram preenchidos!"
        document.getElementById("btn-text").innerText = "Voltar e corrigir"

        button = (document.getElementById("btn-text")) //poderia apenas criar uma classe e nao remover a outra!
        button.classList.remove("btn-success")
        button.classList.add("btn-danger")

        $('#registraDespesa').modal('show')
    }
}

function carregaListaDespesas(despesas = [], filtro = false) {
    if (despesas.length == 0 && filtro == false) {
        despesas = bd.recuperarTodosRegistros()
    }

    var listaDespesas = document.getElementById('listaDespesas')
    listaDespesas.innerHTML = ''

    despesas.forEach(function (d) {
        let linha = listaDespesas.insertRow()
        linha.insertCell(0).innerHTML = `${d.dia} / ${d.mes} / ${d.ano}`
        switch (d.tipo) {
            case '1': d.tipo = 'Alimentação'
                break;
            case '2': d.tipo = 'Educação'
                break;
            case '3': d.tipo = 'Lazer'
                break;
            case '4': d.tipo = 'Saúde'
                break;
            case '5': d.tipo = 'Transporte'
                break;
        }
        linha.insertCell(1).innerHTML = d.tipo
        linha.insertCell(2).innerHTML = d.descricao
        linha.insertCell(3).innerHTML = d.valor

        let btn = document.createElement("button")
        btn.className = 'btn btn-danger'
        btn.innerHTML = '<i class="fa-regular fa-trash-can"></i>'
        btn.id = `id_despesa_${d.id}`
        btn.onclick = function() {
            let id = this.id.replace('id_despesa_', '')
            bd.remover(id)
            id = this.getAttribute("data-id")
            $('#conclusao').modal('show')

            setTimeout(() => {
                window.location.reload()
            }, 3500)

           
        }
        linha.insertCell(4).append(btn)

    })
}

function pesquisarDespesa() {
    let ano = document.getElementById('ano').value
    let mes = document.getElementById('mes').value
    let dia = document.getElementById('dia').value
    let tipo = document.getElementById('tipo').value
    let descricao = document.getElementById('descricao').value
    let valor = document.getElementById('valor').value

    let despesa = new Despesa(ano, mes, dia, tipo, descricao, valor)
    let despesas = bd.pesquisar(despesa)

    carregaListaDespesas(despesas, true)
}

