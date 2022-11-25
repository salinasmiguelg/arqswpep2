import React from 'react';
import { Avatar, Paper, Button, Grid, Box } from '@mui/material';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import TextField from '@mui/material/TextField';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import {es} from 'date-fns/locale';
import CssBaseline from "@material-ui/core/CssBaseline";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from '@mui/material/Typography';
import Cookie from '@mui/icons-material/Cookie';
import { useState, useEffect} from 'react';
import axios from 'axios';






function Justificativos() {
    const [rut, setRut] = useState("");
    const [descripcion, setDescripcion] = useState("");
    const [value, setValue] = useState(new Date());
    const [token, setToken] = useState("");
    const [nombreUsuario, setNombreUsuario] = useState("");
    const [password, setPassword] = useState("");

    const handleChangeRut = (event) => {setRut(event.target.value)};
    const handleChangeDescripcion = (event) => {setDescripcion(event.target.value)};
    const handleChangeUser = (event) => {setNombreUsuario(event.target.value)};
    const handleChangePass = (event) => {setPassword(event.target.value)};

    async function login(event)
    {
        event.preventDefault();
        console.log(password);
        console.log(nombreUsuario);

    try
        {
            const {data} = await axios.post("http://localhost:8080/justificativo/login",
        {
        nombreUsuario: nombreUsuario,
        password : password,
        });
          alert("Se ha iniciado sesión exitosamente.");;
          setNombreUsuario("");
          setPassword("");
          setToken(data.token)
        
        }
    catch(err)
        {
          alert(err);
        }
   }

    function disableWknd(date) {
        if (date.getDay() === 6 || date.getDay() === 0){
        return true;
        }
        return false;
      }

    function addZero(string){
        if(Number(string)<10){
            string = "0"+ string;
        }
        return string;
    }

    async function handleSubmit(event)
    {
        event.preventDefault();
        const newFecha = value.getFullYear() + "/" + addZero(String(Number(value.getMonth()) + 1)) + "/" + addZero(value.getDate());
        console.log(newFecha);
        let config = {
            headers: {
              'Authorization': 'Bearer ' + token
            }
          }
    try
        {
            await axios.post("http://localhost:8080/justificativo",
        {
        rut: rut,
        fecha : newFecha,
        descripcion : descripcion
        },config);
          alert("Se ha ingresado el justificativo exitosamente.");;
          setRut("");
          setValue("");
          setDescripcion("");
        
        }
    catch(err)
        {
          alert("No estás autorizado");
        }
   }

    return(
        <>
            <CssBaseline />
            <AppBar position="fixed">
                <Toolbar href = "/home">
                <Cookie sx={{ display: { xs: 'none', md: 'flex' }, mr: 1 }} />
                <Typography variant="h6" noWrap component="a" href="/" sx={{
              mr: 2,
              display: { xs: 'none', md: 'flex' },
              fontFamily: 'monospace',
              fontWeight: 700,
              letterSpacing: '.3rem',
              color: 'inherit',
              textDecoration: 'none',
            }}>Cookies Chile</Typography>
                </Toolbar>
            </AppBar>
            <Toolbar />
            <Toolbar />
            {(token === "") ?
            <div
                style = {{
                        position: 'absolute', left: '10%', top: '20%',
                        transform: 'translate(-50%, -50%)'

                }}>
            <Grid>
            <TextField id="outlined-basic" label="Nombre de usuario" variant="outlined" onChange={handleChangeUser}/>
            </Grid> 
            <Grid>
            <TextField id="outlined-basic" label="Contraseña" variant="outlined" onChange={handleChangePass}/>
            </Grid>
            <Button variant="outlined"  onClick={login} >Iniciar sesión</Button>
            </div> : null}

            <div style = {{
                        position: 'absolute', left: '50%', top: '50%',
                        transform: 'translate(-50%, -50%)'

                }}>
            <form className="register-form" onSubmit={handleSubmit}>
            <br></br>      
            <h1>Justificativos</h1>
            <p>Ingresa tu justificativo aquí</p>
 
            <Grid>
            <TextField id="outlined-basic" label="RUT" variant="outlined" onChange={handleChangeRut}/>
            </Grid>
 
            <Grid>
            <LocalizationProvider adapterLocale={es} dateAdapter={AdapterDateFns} >
            <DatePicker
            label="Elegir fecha"
            shouldDisableDate={disableWknd}
            value={value}
            onChange={(newValue) => {
                setValue(newValue);
            }}
            renderInput={(params) => <TextField {...params} />}
            />
            </LocalizationProvider>
            </Grid>
 
            <Grid>
            <TextField     sx={{
            width: { sm: 200, md: 300 },
            "& .MuiInputBase-root": {
                height: 100
            }
            }}
            id="outlined-basic" label="Descripción" variant="outlined" onChange={handleChangeDescripcion}/>
            </Grid>

    
    
            <Button variant="outlined" type="submit">Ingresar justificativo</Button>
            </form> 




            </div>
        </>
    );
}

export default Justificativos;