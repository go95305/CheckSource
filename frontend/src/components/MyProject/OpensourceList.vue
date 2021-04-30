<template>
  <div class="container">
    <div class="mapped-component">
      <p>mapped component</p>
    </div>
    <div class="mapped-table-header">
      <div class="title title-1">오픈소스명</div>
      <div class="title title-2">주소</div>
      <div class="title title-3">라이선스</div>
    </div>
    <ul class="responsive-table" :key="index" v-for="(item, index) in list">
      <li class="table-row">
        <div class="col col-1">{{ item.name }}</div>
        <div class="col col-2">{{ item.url }}</div>
        <div class="col col-3">{{ item.obligation }}</div>
      </li>
    </ul>
    <div class="unmapped-component">
      <p>unmapped component</p>
    </div>
    <button class="add-component">추가</button>
    <div class="unmapped-table-header">
      <div class="title title-1">Dependency</div>
      <div class="title title-2">위치</div>
    </div>
    <ul class="responsive-table" :key="index" v-for="(item, index) in unmappedList">
      <li class="table-row">
        <div class="col col-1">{{ item.name }}</div>
        <div class="col col-2">{{ item.origin }}</div>
      </li>
    </ul>
    
  </div>
  
</template>
<script>
export default {
  data() {
    return {
      list: [
        {
          name: "Apache License 2.0",
          url: "http://www.apache.org/licenses/LICENSE-2.0",
          obligation: "Apache License 2.0",
        },
        {
          name: "GNU General Public License v2.0 or later",
          url: "https://opensource.org/licenses/BSD-3-Clause",
          obligation: "Apache License 2.0",
        },
        {
          name: "Eclipse Public License 2.0",
          url: "https://www.eclipse.org/legal/epl-2.0",
          obligation: "Apache License 2.0",
        },
      ],
      unmappedList: [
        {
          name: "Apache License 2.0",
          origin: "http://www.apache.org/licenses/LICENSE-2.0",
        },
        {
          name: "GNU General Public License v2.0 or later",
          origin: "https://opensource.org/licenses/BSD-3-Clause",
        },
        {
          name: "Eclipse Public License 2.0",
          origin: "https://www.eclipse.org/legal/epl-2.0",
        },
      ],
    };
  },
};
</script>
<style scoped src="../../assets/css/MyProject/LicenseList.css"></style>
