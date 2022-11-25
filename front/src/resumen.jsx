import * as React from 'react';
import ResponsiveAppBar from './ResponsiveAppBar';
import axios from 'axios';
import { Box } from '@mui/material';

import { useState, useEffect} from 'react'
import { DataGrid } from '@mui/x-data-grid';





const columns = [
  { field: 'fecha', headerName: 'Fecha', width: 60 },
  { field: 'hora', headerName: 'Hora', width: 80},
  { field: 'run', headerName: 'Run', width: 70},
  { field: 'fileType', headerName: 'Rut', width: 100},
];

function createData(fecha, hora, run, fileType) {
  return { fecha, hora, run, fileType };
}


function ShowTable() {

  const [pageSize, setPageSize] = React.useState(10);

  let baseURL = "http://localhost:8090/fechas" 

  const [ listResumen, setListResumen ] = useState([])

    useEffect(() => {
        getResumen() 
    },[])

    const getResumen = async() => {
        const { data } = await axios.get(baseURL)
        setListResumen(data)
        console.log(data)
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
        checkboxSelection
        disableSelectionOnClick
        experimentalFeatures={{ newEditingApi: true }}
      />
      </Box>
  );
}


const Resumen = () => {
    return (
        <div className='resumen' >
          <Box sx={{ display: 'flex' }}>
                <ResponsiveAppBar/>
          </Box>
          <Box sx={{ width: '95%', p: 9}}>
            <ShowTable/>
         </Box>

        </div>
    )
}

export default Resumen;