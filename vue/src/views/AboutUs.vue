<template>
<div class="normalBox">
  <main id="main-grid">
    <div id="about-us-title">
    <h1>Meet the Development Team</h1>
    </div>
    <div id="Jacob" class="bio">
      <img class="headshot" src="..\img\Jacob.jpg"/>
      <h2>Jacob Garside</h2>
      <p>Visit Jacob's LinkedIn <a href="https://www.linkedin.com/in/jacobgarside/" class="link"> here</a>!</p>
    
    </div> 
    <div id="Nate" class="bio">
      <h2>Nathaniel Lachs</h2>
      <p>Visit Nate's LinkedIn <a href="https://www.linkedin.com/in/nathaniel-lachs/" class="link"> here</a>!</p>
    </div> 
    <div id="Dillon" class="bio">
      <img class="headshot" src="..\img\Dillon.jpg"/>
      <h2>Dillon Donoho</h2>
      <p>Visit Dillon's LinkedIn <a href="https://www.linkedin.com/in/dillondonoho/" class="link"> here</a>!</p>
      
    </div> 
    <div id="Alex" class="bio">
      <img class="headshot" src="..\img\Alex.jpg"/>
      <h2>Alex Spors</h2>
      <p>Visit Alex's LinkedIn <a href="https://www.linkedin.com/in/alexspors/" class="link"> here</a>!</p>
    
    </div> 
    <div id="Niko" class="bio">
      <img class="headshot" src="..\img\Niko.jpg"/>
      <h2>Nikolas Medina</h2>
      <p>Visit Niko's LinkedIn <a href="https://www.linkedin.com/in/njmedina00/" class="link"> here</a>!</p>
   
    </div> 
  </main>
</div>
</template>

<script>

export default {
  components: {
  
  }

}
</script>

<style>
.normalBox{
  margin: 5px;
  background-color: rgba(186,199,202,.9);
  border-radius: 5px;
  padding: 5px;
}

#main-grid {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-template-areas: 
  "title title title"
  "Jacob Niko Dillon"
  "Alex Nate .";
  row-gap: 15px;
  column-gap: 1px;
  align-items: center;
}

#about-us-title {
  grid-area: title;
  margin: 20px;
}

#Jacob {
  grid-area: Jacob;
  text-align: center;
}

#Nate {
  grid-area: Nate;
  text-align: center;
}


#Dillon {
  grid-area: Dillon;
  text-align: center;
}

#Alex {
  grid-area: Alex;
  text-align: center;
}

#Niko {
  grid-area: Niko;
  text-align: center;
}

.headshot {
  border-radius: 50px;
  width: 200px;
  border-style: solid;
  border-color: #005229
}

.link {
  color:#005229 ;
}

.link:hover {
  color: #ffb81f;
}


</style>