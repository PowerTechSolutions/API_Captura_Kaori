import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate

class UsuarioRepositorio {

    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciarSql(){
        jdbcTemplate = ConexaoSqlServer.jdbcTemplate!!
    }

    fun autenticar(senha:String):Boolean{

        var usuario = jdbcTemplate.queryForObject(
            "SELECT * FROM Usuario_Dashboard WHERE Senha = $senha",
            BeanPropertyRowMapper(Usuario::class.java)
        )

        if (usuario != null){
            return true
        }else{
            return false
        }

    }

    fun resgatarinfo(senha:String):Usuario{

        var usuario = jdbcTemplate.queryForObject(
            "SELECT * FROM Usuario_Dashboard WHERE Senha = $senha",
            BeanPropertyRowMapper(Usuario::class.java)
        )

        return usuario

    }

}