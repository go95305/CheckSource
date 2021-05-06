<template>
  <div id="form_section">
    <div class="title">
      <h2>새로운 Component 추가</h2>
    </div>
    <div class="dependency-title">
      <h3>Dependency</h3>
    </div>
    <form class="dependency">
      <div class="path">
        <label class="path-label">path</label>
        <input type="text" placeholder="path" />
      </div>
      <div class="version">
        <label class="version-label">version</label>
        <input type="text" placeholder="version" />
      </div>
      <div class="complianceUrl">
        <label class="ComplianceUrl-label">ComplianceUrl</label>
        <input type="text" placeholder="ComplianceUrl" />
      </div>
    </form>
    <form class="component">
      <div>
         <h3>Component 생성</h3>
      </div>

    </form>
  </div>
</template>
<script>
export default {
  name: "AddComponent",
};
</script>
<style scoped src="../../assets/css/MyProject/AddComponent.css"></style>
