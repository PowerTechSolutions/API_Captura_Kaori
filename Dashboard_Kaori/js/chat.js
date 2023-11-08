var show_lit = false
function literate() {
    if (show_lit == true) {
        show_lit = false
        lit_dropdown.style.display = 'none'
    } else {
        show_lit = true
        lit_dropdown.style.display = 'block'
    }
}

function clickclose3() {
    if (show_lit == true) {
        show_lit = false
        lit_dropdown.style.display = 'none'
    }
}

var show_ram = false
function ram() {
    show_ram = true
    info_ram.style.display = 'block'
    const input_ram = document.getElementById('input_ram');
    const slider = document.getElementById('slider');
    const numberOfDivs = parseInt(input_ram.value);

    // Clear existing divs
    slider.innerHTML = '';

    for (let i = 0; i < numberOfDivs; i++) {
        const newDiv = document.createElement('div');
        newDiv.classList.add('form-div');
        newDiv.innerHTML = `
            <h3>RAM Form ${i + 1}</h3>
            <label for="ram_name${i}">Fabricante:</label>
            <input type="text" id="ram_name${i}" name="ram_name">
            <label for="ram_age${i}">Capacidade:</label>
            <input type="number" id="ram_age${i}" name="ram_age">
        `;
        slider.appendChild(newDiv);
    }
}

function close_ram() {
    if (show_ram == true) {
        show_ram = false
        info_ram.style.display = 'none'
    }
}

// var show_disc = false
// function disco() {
//     show_disc = true
//     info_disc.style.display = 'block'
    
// }
var show_disc = false
function disco() {
    show_disc = true
    info_disc.style.display = 'block'
    const input_disco = document.getElementById('input_disco');
    const slider = document.getElementById('disc_1');
    const numberOfDivs = parseInt(input_disco.value);

    // Clear existing divs
    slider.innerHTML = '';

    for (let i = 0; i < numberOfDivs; i++) {
        const newDiv = document.createElement('div');
        newDiv.classList.add('form-div');
        newDiv.innerHTML = `
            <h3>Disco ${i + 1}</h3>
            <label for="name${i}">Fabricante:</label>
            <input type="text" id="name${i}" name="name">
            <label for="age${i}">Capacidade:</label>
            <input type="number" id="age${i}" name="age">
        `;
        slider.appendChild(newDiv);
    }
}


function close_disc() {
    if (show_disc == true) {
        show_disc = false
        info_disc.style.display = 'none'
    }
}

var show = false
function cadastrar() {
    show = true
    cadastrar_novo_usuario.style.display = 'block'
}

function fechar() {
    if (show == true) {
        show = false
        cadastrar_novo_usuario.style.display = 'none'
    }
}

var show_adicionar = false
function adicionar() {
    show_adicionar = true
    cadastrar_novo_usuario2.style.display = 'block'
}

function fechar_adicionar() {
    if (show_adicionar == true) {
        show_adicionar = false
        cadastrar_novo_usuario2.style.display = 'none'
    }
}


var show_ram2 = false;

function ram2() {
    show_ram2 = true;
    info_ram2.style.display = 'block';
    const input_ram2 = document.getElementById('input_ram2');
    const ram2_slider = document.getElementById('ram2_slider');
    const numberOfDivs = parseInt(input_ram2.value);

    // Clear existing divs
    ram2_slider.innerHTML = '';

    for (let i = 0; i < numberOfDivs; i++) {
        const newDiv = document.createElement('div');
        newDiv.classList.add('form-div');
        newDiv.innerHTML = `
            <h3>RAM Form ${i + 1}</h3>
            <label for="ram_name${i}">Fabricante:</label>
            <input type="text" id="ram_name${i}" name="ram_name">
            <label for="ram_capacity${i}">Capacidade:</label>
            <input type="number" id="ram_capacity${i}" name="ram_capacity">
        `;
        ram2_slider.appendChild(newDiv);
    }
}

function close_ram2() {
    if (show_ram2 == true) {
        show_ram2 = false
        info_ram2.style.display = 'none'
    }
}

var show_disc2 = false;

function disco2() {
    show_disc2 = true;
    info_disc2.style.display = 'block';
    const input_disco2 = document.getElementById('input_disco2');
    const disco2_slider = document.getElementById('disco2_slider');
    const numberOfDivs = parseInt(input_disco2.value);

    // Clear existing divs
    disco2_slider.innerHTML = '';

    for (let i = 0; i < numberOfDivs; i++) {
        const newDiv = document.createElement('div');
        newDiv.classList.add('form-div');
        newDiv.innerHTML = `
            <h3>Disk Form ${i + 1}</h3>
            <label for="disk_name${i}">Fabricante:</label>
            <input type="text" id="disk_name${i}" name="disk_name">
            <label for="disk_capacity${i}">Capacidade:</label>
            <input type="number" id="disk_capacity${i}" name="disk_capacity">
        `;
        disco2_slider.appendChild(newDiv);
    }
}

function close_disc2() {
    if (show_disc2 == true) {
        show_disc2 = false;
        info_disc2.style.display = 'none';
    }
}

