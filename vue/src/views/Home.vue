<template>
  <div class="home">
    <div id="details">
    <h1>Current leaderboard</h1>
    <p>(to do: replace with component) Lorem ipsum dolor, sit amet consectetur adipisicing elit. Modi ab ipsa consequatur vitae veritatis veniam, maxime molestias doloribus exercitationem aspernatur fuga tempora perspiciatis dolore. Placeat dolor praesentium modi similique totam!</p>
    </div>
    <div id="selector">
      <h3>When clicked, the items below will change this--></h3>
      <ul>
        <li>Leaderboard</li>
        <li>Next Match</li>
        <li>Following Match</li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>
<style>
.home{
  background-image: url('../../Home.png');
  background-repeat: no-repeat;
  padding: 5px;
  display: grid;
  grid-template-columns: 1fr 3fr;
  grid-template-areas: "selector details";
}
#selector{
  grid-area: selector;
  background-color: rgba(255, 255, 255, 0);
}
#selector ul{
  list-style-type: none;
}
#details{
  padding: 5px;
  grid-area: details;
}
</style>