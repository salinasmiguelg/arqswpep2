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




function HoraExtra() {
    const [rut, setRut] = useState("");
    const [horas, setHoras] = useState("");
    const [descripcion, setDescripcion] = useState("");
    const [value, setValue] = useState(new Date());

    const handleChangeRut = (event) => {setRut(event.target.value)};
    const handleChangeDescripcion = (event) => {setDescripcion(event.target.value)};
    const handleChangeHoras = (event) => {setHoras(event.target.value)};


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
    try
        {
         await axios.post("http://localhost:8080/sobretiempo",
        {
        rut: rut,
        fecha : newFecha,
        horasAutorizadas : horas,
        descripcion : descripcion
        });
          alert("User Registation Successfully");;
          setRut("");
          setValue("");
          setDescripcion("");
          setHoras("");
        
        }
    catch(err)
        {
          alert("No se ha podido subir la autorización de horas extras");
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
            <div
            style={{
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                height: '100vh',
            }}>

            <form className="register-form" onSubmit={handleSubmit}>
            <br></br>      
            <h1>Horas extras</h1>
            <p>Ingresa las horas extras que tengas autorizadas aquí</p>
 
            <Grid>
            <TextField id="outlined-basic" label="RUT" variant="outlined" onChange={handleChangeRut}/>
            </Grid>

            <Grid>
            <TextField id="outlined-basic" type="number" label="Cantidad de horas" variant="outlined" inputProps={{ inputMode: 'numeric', pattern: '[0-9]*' }} onChange={handleChangeHoras}/>
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

    
    
            <Button variant="outlined" type="submit">Ingresar horas extras</Button>
            </form> 




            </div>
        </>
    );
}

export default HoraExtra;