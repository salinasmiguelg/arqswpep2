import * as React from 'react';
import axios from 'axios';
import { Box } from '@mui/material';

import { useState, useEffect} from 'react';
import { DataGrid } from '@mui/x-data-grid';
import CssBaseline from "@material-ui/core/CssBaseline";

import Toolbar from "@material-ui/core/Toolbar";
import Typography from '@mui/material/Typography';
import Cookie from '@mui/icons-material/Cookie';
import AppBar from "@material-ui/core/AppBar";
import $ from 'jquery';




function Sueldos() {
    const columns = [
    { field: 'id', headerName: 'ID', width: 10 },
    { field: 'rut', headerName: 'Rut', width: 100 },
    { field: 'nombreCompleto', headerName: 'Nombre', width: 120},
    { field: 'categoria', headerName: 'Categoría', width: 50},
    { field: 'servicioEmpresa', headerName: 'Años de servicio', width: 10},
    { field: 'sueldoFijoMensual', headerName: 'Sueldo fijo', width: 100},
    { field: 'bonificacionHrsExtras', headerName: 'Bono hrs extras', width: 100},
    { field: 'bonificacionTiempoServicio', headerName: 'Bono años de servicio', width: 100},
    { field: 'bonificacionPuntualidad', headerName: 'Bono puntualidad', width: 100},
    { field: 'descuentoTardanza', headerName: 'Descuento tardanzas', width: 100},
    { field: 'descuentoRetiro', headerName: 'Descuento retiros', width: 100},
    { field: 'sueldoBruto', headerName: 'Sueldo bruto', width: 100},
    { field: 'cotizacionPrevisional', headerName: 'Cot. previsional', width: 100},
    { field: 'cotizacionSalud', headerName: 'Cot. salud', width: 100},
    { field: 'montoSueldoFinal', headerName: 'Sueldo a pagarse', width: 100},
    ];




    function ShowTable() {

    const [pageSize, setPageSize] = React.useState(10);

    let baseURL = "http://localhost:8080/reporte/calcularReportes" 

    const [ listResumen, setListResumen ] = useState([])

        useEffect(() => {
            getResumen()
        },[])

        const getResumen = async() => {
            const { data } = await axios.post(baseURL)
            setListResumen(addID(data))
            console.log(listResumen)
        }

    function addID(data){
        var ret= [];
        for (var i = 1; i <= data.length; i++){
            $.extend( data[i-1], {id:String(i)});
            ret.push(data[i-1]);
        }
        return ret
    }


    return (
        <Box

        >
        <DataGrid
            autoHeight
            autoWidth
            rows={listResumen}
            columns={columns}
            pageSize={pageSize}
            onPageSizeChange={(newPageSize) => setPageSize(newPageSize)}
            getRowHeight={() => 'auto'}
            rowsPerPageOptions={[10,25,100]}
            pagination
            disableSelectionOnClick
            experimentalFeatures={{ newEditingApi: true }}
        />
        </Box>
    );
    }



    return (
        <div className='resumen' >
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

        <Box sx={{width:"95%", p:"10"}}>
            <ShowTable/>
        

        </Box>
        </div>
    )
}

export default Sueldos;