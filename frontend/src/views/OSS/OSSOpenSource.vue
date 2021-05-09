<template>
    <div>
        <div class="search-bar-div">
            <search-bar
                :placeHolderText="'검색할 오픈소스를 입력하세요.'"
                :filterList="filterList"
            ></search-bar>
        </div>
        <div>
            <table class="oss-table">
                <thead class="oss-table-thead">
                    <th>NAME</th>
                    <th>URL</th>
                    <th>LICENSE</th>
                    <th>COPYRIGHT</th>
                </thead>
                <tbody class="oss-table-tbody">
                    <tr
                        class="oss-table-tr"
                        v-for="(opensource, index) in openSourceList"
                        :key="`${index}_openSourceList`"
                        @click="GoDetail(opensource)"
                    >
                        <td>{{ opensource.name }}</td>
                        <td>{{ opensource.url }}</td>
                        <td>{{ opensource.license }}</td>
                        <td>{{ opensource.copyright }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="oss-pagination">
            <pagination-remote
                :currentPage="page"
                :lastPage="20"
                @changePage="ChangePage"
            ></pagination-remote>
        </div>
    </div>
</template>
<script>
import "@/assets/css/OSS/OSSTable.css";
import SearchBar from "../../components/SearchBar/SearchBar.vue";
import PaginationRemote from "../../components/Pagination/PaginationRemote.vue";
export default {
    name: "OSSOpenSource",
    components: { SearchBar, PaginationRemote },
    data() {
        return {
            filterList: ["NAME", "LICENSE"],
            openSourceList: [],
            page: 1,
            size: 10,
        };
    },
    created() {
        if (this.$route.query.page) {
            this.page = Number(this.$route.query.page);
        }
        if (this.$route.query.size) {
            this.size = Number(this.$route.query.size);
        }

        this.GetList();
    },
    methods: {
        GetList: function () {
            this.openSourceList = [
                {
                    id: "1111",
                    name: "GLEW",
                    url: "https://github.com/nigels-com/glew",
                    license: "GLEW Modified BSD3 License",
                    copyright: "Copyright 2008-2016",
                },
                {
                    id: "1112",
                    name: "httpx",
                    url: "https://github.com/encode/httpx",
                    license: "GLEW Modified BSD3 License",
                    copyright: "Copyright 2008-2016",
                },
                {
                    id: "1113",
                    name: "GLEW",
                    url: "https://github.com/nigels-com/glew",
                    license: "GLEW Modified BSD3 License",
                    copyright: "Copyright 2008-2016",
                },
                {
                    id: "1114",
                    name: "httpx",
                    url: "https://github.com/encode/httpx",
                    license: "GLEW Modified BSD3 License",
                    copyright: "Copyright 2008-2016",
                },
                {
                    id: "1115",
                    name: "GLEW",
                    url: "https://github.com/nigels-com/glew",
                    license: "GLEW Modified BSD3 License",
                    copyright: "Copyright 2008-2016",
                },
                {
                    id: "1116",
                    name: "httpx",
                    url: "https://github.com/encode/httpx",
                    license: "GLEW Modified BSD3 License",
                    copyright: "Copyright 2008-2016",
                },
                {
                    id: "1117",
                    name: "GLEW",
                    url: "https://github.com/nigels-com/glew",
                    license: "GLEW Modified BSD3 License",
                    copyright: "Copyright 2008-2016",
                },
                {
                    id: "1118",
                    name: "httpx",
                    url: "https://github.com/encode/httpx",
                    license: "GLEW Modified BSD3 License",
                    copyright: "Copyright 2008-2016",
                },
            ];
        },
        ChangePage: function (page) {
            this.page = page;
            this.$router.push({
                query: { page: page, size: this.size },
            });
        },
        GoDetail: function (opensource) {
            this.$router.push({
                name: "OSSDetailOpenSource",
                query: { id: opensource.id },
            });
        },
    },
};
</script>
<style scoped src="@/assets/css/OSS/OSSOpenSource.css"></style>
