<template>
  <div class="container">
    <div class="mapped-component">
      <p>mapped component</p>
    </div>
    <div class="mapped-table-header" :class="{ sideBar: containerWidth }">
      <div class="title title-1">오픈소스명</div>
      <div class="title title-2">주소</div>
      <div class="title title-3">라이선스</div>
    </div>
    <ul
      class="responsive-table"
      :key="`${index}_mapped`"
      v-for="(item, index) in list"
      :class="{ sideBar: containerWidth }"
    >
      <li class="table-row" @click="sidebar(item.version)">
        <div class="col col-1">{{ item.name }}</div>
        <div class="col col-2">{{ item.url }}</div>
        <div class="col col-3">{{ item.obligation }}</div>
      </li>
    </ul>
    <div class="unmapped-component">
      <p>unmapped component</p>
    </div>
    <button class="add-component" @click="addComponent">Component 추가</button>
    <div class="unmapped-table-header" :class="{ sideBar: containerWidth }">
      <div class="title utitle-1">Dependency</div>
      <div class="title utitle-2">위치</div>
    </div>
    <ul
      class="responsive-table"
      :key="`${idx}_unmapped`"
      v-for="(item, idx) in unmappedList"
      :class="{ sideBar: containerWidth }"
    >
      <li class="table-row">
        <div class="col ucol-1">{{ item.name }}</div>
        <div class="col ucol-2">{{ item.origin }}</div>
      </li>
    </ul>
    <div class="component-specific" v-if="sidebarShow">
      <div>
        <button class="closebtn" @click="closeSideBar">&times;</button>
      </div>
      <div class="component-version">
        <p>License Version</p>
        <p class="version">{{ version }}</p>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      sidebarShow: false,
      containerWidth: false,
      version: "",
      list: [
        {
          name: "Apache License 2.0",
          url: "http://www.apache.org/licenses/LICENSE-2.0",
          obligation: "Apache License 2.0",
          version: "apache.0.1",
        },
        {
          name: "GNU General Public License v2.0 or later",
          url: "https://opensource.org/licenses/BSD-3-Clause",
          obligation: "Apache License 2.0",
          version: "apache.2",
        },
        {
          name: "Eclipse Public License 2.0",
          url: "https://www.eclipse.org/legal/epl-2.0",
          obligation: "Apache License 2.0",
          version: "axios.0.1",
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
  methods: {
    sidebar(versions) {
      this.sidebarShow = true;
      this.containerWidth = true;
      this.version = versions;
    },
    closeSideBar() {
      this.sidebarShow = false;
      this.containerWidth = false;
    },
    addComponent() {
      this.$router.push({name:"AddComponent"});
    },
  },
};
</script>
<style scoped src="../../assets/css/MyProject/OpensourceList.css"></style>
