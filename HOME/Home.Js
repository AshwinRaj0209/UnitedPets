function ourevents(){
    let hide=document.getElementById("Our-mission");
    hide.style.display=("none");
    let show=document.getElementById("Our-events");
    show.style.display=("grid");
    show.style.transform=("translateY(0em) translateX(2em)");
    let img=document.getElementById("Our-events-img");
    img.style.transform=("translateX(12em)");
    let evebut=document.getElementById("Home-content6-our-events-header");
    evebut.style.backgroundColor=("#d61c62");
    let misbut=document.getElementById("Home-content6-our-mission-header");
    misbut.style.backgroundColor=("#0d6efd");
    
}

function ourmission(){
    let hide=document.getElementById("Our-events");
    hide.style.display=("none");
    let show=document.getElementById("Our-mission");
    show.style.display=("grid");
    let evebut=document.getElementById("Home-content6-our-events-header");
    evebut.style.backgroundColor=("#0d6efd");
    let misbut=document.getElementById("Home-content6-our-mission-header");
    misbut.style.backgroundColor=("#d61c62");
}
