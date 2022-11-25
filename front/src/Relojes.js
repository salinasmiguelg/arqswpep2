import React from "react";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from '@mui/material/Typography';  
import CssBaseline from "@material-ui/core/CssBaseline";
import Container from "@material-ui/core/Container";
import Paper from "@material-ui/core/Paper";
import Grid from "@material-ui/core/Grid";
import Divider from "@material-ui/core/Divider";
import { useDropzone } from "react-dropzone";
import RootRef from "@material-ui/core/RootRef";
import { makeStyles } from "@material-ui/core/styles";
import CircularProgress from "@material-ui/core/CircularProgress";
import { green } from "@material-ui/core/colors";
import Fab from "@material-ui/core/Fab";
import CheckIcon from "@material-ui/icons/Check";
import CloudUpload from "@material-ui/icons/CloudUpload";
import clsx from "clsx";
import { LinearProgress } from "@material-ui/core";
import axios from "axios";
import { Box } from '@mui/material';
import { useState, useEffect} from 'react';
import { DataGrid } from '@mui/x-data-grid';
import Cookie from '@mui/icons-material/Cookie';


const columns = [
  { field: 'fecha', headerName: 'Fecha', width: 200 },
  { field: 'hora', headerName: 'Hora', width: 200},
  { field: 'run', headerName: 'Run', width: 200},
  { field: 'fileType', headerName: 'Tipo', width: 200},
];

function createData(fecha, hora, run, fileType) {
  return { fecha, hora, run, fileType };
}


function ShowTable() {

  const [pageSize, setPageSize] = React.useState(10);

  let baseURL = "http://localhost:8080/relojes/datoHora" 

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
  );
}

const useStyles = makeStyles((theme) => ({
  dropzoneContainer: {
    height: 300,
    background: "#efefef",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    borderStyle: "dashed",
    borderColor: "#aaa",
  },
  preview: {
    width: 1000,
    height: 1000,
    margin: "auto",
    display: "block",
    marginBottom: theme.spacing(2),
    objectFit: "contain",
  },
  wrapper: {
    margin: theme.spacing(1),
    position: "relative",
  },
  buttonSuccess: {
    backgroundColor: green[500],
    "&:hover": {
      backgroundColor: green[700],
    },
  },
  fabProgress: {
    color: green[500],
    position: "absolute",
    top: -6,
    left: -6,
    zIndex: 1,
  },
  buttonProgress: {
    color: green[500],
    position: "absolute",
    top: "50%",
    left: "50%",
    marginTop: -12,
    marginLeft: -12,
  },
}));

function Relojes() {
  const classes = useStyles();
  const [loading, setLoading] = React.useState(false);
  const [success, setSuccess] = React.useState(false);
  const [file, setFile] = React.useState();
  const [preview, setPreview] = React.useState();
  const [percent, setPercent] = React.useState(0);
  const [downloadUri, setDownloadUri] = React.useState();
  const [selectedImageFile, setSelectedImageFile] = React.useState();

  
  const buttonClassname = clsx({
    [classes.buttonSuccess]: success,
  });

  const onDrop = React.useCallback((acceptedFiles) => {
    const fileDropped = acceptedFiles[0];
    setFile(fileDropped);
    setSuccess(false);
    setPercent(0);
  });

  const { getRootProps, getInputProps } = useDropzone({
    multiple: false,
    onDrop,
  });

  const { ref, ...rootProps } = getRootProps();

  const uploadFile = async () => {
    try {
      setSuccess(false);
      setLoading(true);
      const formData = new FormData();
      formData.append("file", file);
      const API_URL = "http://localhost:8080/relojes/datoHora/datesupload/";
      const response = await axios.post(API_URL, formData);

      setDownloadUri(response.data.fileDownloadUri);
      setSuccess(true);
      setLoading(false);
    } catch (err) {
      alert(err.message);
    }
  };

  const onCropSave = ({ file, preview }) => {
    setPreview(preview);
    setFile(file);
    setSuccess(false);
    setPercent(0);
  };

  return (
    <>
      <CssBaseline />
      <AppBar position="fixed">
        <Toolbar>
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

      <Container maxWidth="md">
        <Paper elevation={4}>
          <Grid container>
            <Grid item xs={12}>
              <Typography align="center" style={{ padding: 16 }}>
                Sube aquí tu archivo (json o txt)
              </Typography>
              <Divider />
            </Grid>

            <Grid item xs={6} style={{ padding: 16 }}>
              <RootRef rootRef={ref}>
                <Paper
                  {...rootProps}
                  elevation={0}
                  className={classes.dropzoneContainer}
                >
                  <input {...getInputProps()} />
                  <p>Arrastra o haz clic aquí para subir un arhivo</p>
                </Paper>
              </RootRef>
            </Grid>

            <Grid item xs={6} style={{ padding: 16 }}>
              {/*  */}
              {file && (
                <>
                  <Divider />
                  <Grid
                    container
                    style={{ marginTop: 16 }}
                    alignItems="center"
                    spacing={3}
                  >
                    <Grid item xs={2}>
                      <div className={classes.wrapper}>
                        <Fab
                          aria-label="save"
                          color="primary"
                          className={buttonClassname}
                          onClick={uploadFile}
                        >
                          {success ? <CheckIcon /> : <CloudUpload />}
                        </Fab>
                        {loading && (
                          <CircularProgress
                            size={68}
                            className={classes.fabProgress}
                          />
                        )}
                      </div>
                    </Grid>

                    <Grid item xs={10}>
                      {file && (
                        <Typography variant="body">{file.name}</Typography>
                      )}
                      {loading && (
                        <div>
                          <LinearProgress
                            variant="determinate"
                            value={percent}
                          />
                          <div
                            style={{
                              display: "flex",
                              alignItems: "center",
                              justifyContent: "center",
                            }}
                          >
                            <Typography variant="body">{percent}%</Typography>
                          </div>
                        </div>
                      )}

                      {success && (
                        <Typography>
                          File Upload Success!{" "}
                          <a href={downloadUri} target="_blank">
                            File Url
                          </a>
                        </Typography>
                      )}
                    </Grid>
                    
                  </Grid>
                </>
              )}
              {/*  */}
            </Grid>
            
          </Grid>
          
        </Paper>
      </Container>
      <Box sx={{ width: '100%'}}>
      <ShowTable/>
      </Box>
    </>
  );
}

export default Relojes;
