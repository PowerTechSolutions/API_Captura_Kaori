var express = require("express");
var router = express.Router();

var medidaController = require("../controllers/medidaController");

router.get("/ultimas/:FKUnidade", function (req,res){
    medidaController.log_alertas(req, res);
});

router.get("/tempo_real/:FKUnidade", function (req,res){
    medidaController.tempo_real_log_alertas(req, res);
});

router.get("/buscarDiscos/:FKMAQUINA", function (req,res){
    medidaController.buscarDiscos(req,res);
});

router.get("/ultimas_CPU/:FKMAQUINA", function (req,res){
    medidaController.ultimas_CPU(req,res);
});

router.get("/tempo-real_CPU/:FKMAQUINA", function (req,res){
    medidaController.tempo_real_CPU(req,res);
});

router.get("/ultimas_RAM/:FKMAQUINA", function (req,res){
    medidaController.ultimas_RAM(req,res);
});

router.get("/tempo-real_RAM/:FKMAQUINA", function (req,res){
    medidaController.tempo_real_RAM(req,res);
});

router.get("/contar_MF/:IDEmpresaVar", function (req,res){
    medidaController.contar_MF(req,res);
});

module.exports = router;