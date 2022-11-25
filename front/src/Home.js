import React from 'react';
import { Avatar, Paper, Button, Grid, Box } from '@mui/material';
import AccessAlarmIcon from '@mui/icons-material/AccessAlarm';
import FeedIcon from '@mui/icons-material/Feed';
import FactCheck from '@mui/icons-material/FactCheck';
import EventAvailable from '@mui/icons-material/Event';
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';
import CssBaseline from "@material-ui/core/CssBaseline";
import AppBar from "@material-ui/core/AppBar";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from '@mui/material/Typography';
import Cookie from '@mui/icons-material/Cookie';




  



function Home() {
    const avatarStyle={backgroundColor:'#005588'}
    const paperStyle={padding :20,height:'20vh',width:260, margin:"20px auto"}

    const navigateResumen = () => {
        window.location.href = '/resumen';
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
            <Grid container spacing={4}>
                    <Grid item xs>
                    <Paper elevation={10} style={paperStyle}>
                        <Grid align='center'>
                            <Avatar style={avatarStyle}><EventAvailable/></Avatar>
                            <h5>Justificativos</h5>
                            <Button href = "/justificativos" variant="contained" color="primary" endIcon={<ArrowForwardIosIcon />} margin="normal" ></Button>
                        </Grid>
                    </Paper>
                </Grid>
            
            <Grid item xs>
                <Paper elevation={10} style={paperStyle}>
                    <Grid align='center'>
                        <Avatar style={avatarStyle}><AccessAlarmIcon/></Avatar>
                        <h5>Relojes</h5>
                        <Button href = "/relojes" variant="contained" color="primary" endIcon={<ArrowForwardIosIcon />} margin="normal"></Button>
                    </Grid>
                </Paper>
            </Grid>

            <Grid item xs>
                <Paper elevation={10} style={paperStyle}>
                    <Grid align='center'>
                        <Avatar style={avatarStyle}><FactCheck/></Avatar>
                        <h5>Planilla de sueldos</h5>
                        <Button href = "/sueldos" variant="contained" color="primary" endIcon={<ArrowForwardIosIcon />} margin="normal" ></Button>
                    </Grid>
                </Paper>
                </Grid>

            <Grid item xs>
                <Paper elevation={10} style={paperStyle}>
                    <Grid align='center'>
                        <Avatar style={avatarStyle}><FeedIcon/></Avatar>
                        <h5>Horas Extras</h5>
                        <Button href = "/horaextra" variant="contained" color="primary" endIcon={<ArrowForwardIosIcon />} margin="normal" ></Button>
                    </Grid>
                </Paper>
                </Grid>
            </Grid>
        </>
    );
}

export default Home;