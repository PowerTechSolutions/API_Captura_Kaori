var medidaModel = require("../models/medidaModel");

function log_alertas(req, res) {

    var FKUnidade = req.params.FKUnidade;

    console.log(`Recuperando Quantidade de alertas`);

    medidaModel.log_alertas(FKUnidade).then(function (resultado) {
        if (resultado.length > 0) {
            res.status(200).json(resultado);
        } else {
            res.status(204).send("Nenhum resultado encontrado!")
        }
    }).catch(function (erro) {
        console.log(erro);
        console.log("Houve um erro ao buscar as ultimas medidas.", erro.sqlMessage);
        res.status(500).json(erro.sqlMessage);
    });
}

function tempo_real_log_alertas(req, res) {

    var FKUnidade = req.params.FKUnidade;

    console.log(`Recuperando Quantidade de alertas`);

    medidaModel.tempo_real_log_alertas(FKUnidade).then(function (resultado) {
        if (resultado.length > 0) {
            res.status(200).json(resultado);
        } else {
            res.status(204).send("Nenhum resultado encontrado!")
        }
    }).catch(function (erro) {
        console.log(erro);
        console.log("Houve um erro ao buscar as ultimas medidas.", erro.sqlMessage);
        res.status(500).json(erro.sqlMessage);
    });
}

function buscarDiscos(req, res) {

    var FKMAQUINA = req.params.FKMAQUINA;

    medidaModel.buscarDiscos(FKMAQUINA).then(function (resultado) {
        if (resultado.length > 0) {
            res.status(200).json(resultado);
        } else {
            res.status(204).send("Nenhum resultado encontrado!")
        }
    }).catch(function (erro) {
        console.log(erro);
        console.log("Houve um erro ao buscar as medidas.", erro.sqlMessage);
        res.status(500).json(erro.sqlMessage);
    });
}

function ultimas_CPU(req, res) {

    var FKMAQUINA = req.params.FKMAQUINA;

    medidaModel.ultimas_CPU(FKMAQUINA).then(function (resultado) {
        if (resultado.length > 0) {
            res.status(200).json(resultado);
        } else {
            res.status(204).send("Nenhum resultado encontrado!")
        }
    }).catch(function (erro) {
        console.log(erro);
        console.log("Houve um erro ao buscar as medidas.", erro.sqlMessage);
        res.status(500).json(erro.sqlMessage);
    });
}

function tempo_real_CPU(req, res) {

    var FKMAQUINA = req.params.FKMAQUINA;

    medidaModel.tempo_real_CPU(FKMAQUINA).then(function (resultado) {
        if (resultado.length > 0) {
            res.status(200).json(resultado);
        } else {
            res.status(204).send("Nenhum resultado encontrado!")
        }
    }).catch(function (erro) {
        console.log(erro);
        console.log("Houve um erro ao buscar as medidas.", erro.sqlMessage);
        res.status(500).json(erro.sqlMessage);
    });
}

function ultimas_RAM(req, res) {

    var FKMAQUINA = req.params.FKMAQUINA;

    medidaModel.ultimas_RAM(FKMAQUINA).then(function (resultado) {
        if (resultado.length > 0) {
            res.status(200).json(resultado);
        } else {
            res.status(204).send("Nenhum resultado encontrado!")
        }
    }).catch(function (erro) {
        console.log(erro);
        console.log("Houve um erro ao buscar as medidas.", erro.sqlMessage);
        res.status(500).json(erro.sqlMessage);
    });
}

function tempo_real_RAM(req, res) {

    var FKMAQUINA = req.params.FKMAQUINA;

    medidaModel.tempo_real_RAM(FKMAQUINA).then(function (resultado) {
        if (resultado.length > 0) {
            res.status(200).json(resultado);
        } else {
            res.status(204).send("Nenhum resultado encontrado!")
        }
    }).catch(function (erro) {
        console.log(erro);
        console.log("Houve um erro ao buscar as medidas.", erro.sqlMessage);
        res.status(500).json(erro.sqlMessage);
    });
}

function contar_MF(req, res) {

    var IDEmpresa = req.params.IDEmpresaVar;

    medidaModel.contar_MF(IDEmpresa).then(function (resultado) {
        if (resultado.length > 0) {
            res.status(200).json(resultado);
        } else {
            res.status(204).send("Nenhum resultado encontrado!")
        }
    }).catch(function (erro) {
        console.log(erro);
        console.log("Houve um erro ao buscar as medidas.", erro.sqlMessage);
        res.status(500).json(erro.sqlMessage);
    });
}

module.exports = {
    log_alertas,
    tempo_real_log_alertas,
    buscarDiscos,
    ultimas_CPU,
    tempo_real_CPU,
    ultimas_RAM,
    tempo_real_RAM,
    contar_MF
}