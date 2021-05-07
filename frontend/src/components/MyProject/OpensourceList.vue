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
    <ul
      class="responsive-table"
      :key="`${index}_mapped`"
      v-for="(item, index) in list"
    >
      <li class="table-row" @click="sidebar">
        <div class="col col-1">{{ item.name }}</div>
        <div class="col col-2">{{ item.url }}</div>
        <div class="col col-3">{{ item.obligation }}</div>
      </li>

      <div class="component-specific">
        <p class="version">License Version: {{ item.version }}</p>
        <p class="package-type">Packge Type: {{ item.packageType }}</p>
        <p class="artifact-id">Artifact Id: {{ item.artifactId }}</p>
        <p class="group-id">Group Id: {{ item.groupId }}</p>
      </div>
    </ul>
    <div class="unmapped-component">
      <p>unmapped component</p>
    </div>
    <button class="add-component" @click="addComponent">Component 추가</button>
    <div class="unmapped-table-header">
      <div class="title utitle-1">Dependency</div>
      <div class="title utitle-2">위치</div>
    </div>
    <ul
      class="responsive-table"
      :key="`${idx}_unmapped`"
      v-for="(item, idx) in unmappedList"
    >
      <li class="table-row">
        <div class="col ucol-1">{{ item.name }}</div>
        <div class="col ucol-2">{{ item.origin }}</div>
      </li>
    </ul>
  </div>
</template>
<script>
export default {
  data() {
    return {
      sidebarShow: [],
      containerWidth: false,
      version: "",
      packageType: "",
      artifactId: "",
      groupId: "",
      list: [
        {
          name: "Apache License 2.0",
          url: "http://www.apache.org/licenses/LICENSE-2.0",
          obligation: "Apache License 2.0",
          version: "apache.0.1",
          packageType: "s",
          artifactId: "a",
          groupId: "g",
        },
        {
          name: "GNU General Public License v2.0 or later",
          url: "https://opensource.org/licenses/BSD-3-Clause",
          obligation: "Apache License 2.0",
          version: "apache.2",
          packageType: "s",
          artifactId: "a",
          groupId: "g",
        },
        {
          name: "Eclipse Public License 2.0",
          url: "https://www.eclipse.org/legal/epl-2.0",
          obligation: "Apache License 2.0",
          version: "axios.0.1",
          packageType: "s",
          artifactId: "a",
          groupId: "g",
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
    sidebar(event) {
      let elem=event.target;
      while(!elem.classList.contains('table-row')){
        elem=elem.parentNode;

        if(elem.nodeName=='BODY'){
          elem=null;
          return;
        }
      }
      console.log(elem.classList.contains('choice'));
      // console.log(event.target);
      if (elem.classList.contains("choice")) {
        elem.classList.remove("choice");
      } else {
        
        // let dropbtns = this.$el.getElementsByClassName("choice");
        // for (let i = 0; i < dropbtns.length; i++) {
        //   dropbtns[i].classList.remove("choice");
        // }
        elem.classList.add("choice");
      }
    },
    closeSideBar() {
      this.sidebarShow = false;
      this.containerWidth = false;
    },
    addComponent() {
      this.$router.push({ name: "AddComponent" });
    },
  },
};
</script>
<style scoped src="../../assets/css/MyProject/OpensourceList.css"></style>
