<template>
    <div>
        <repository-card
            v-for="(repository, index) in repositoryList"
            :key="`${index}_repositoryList`"
            :repository="repository"
        ></repository-card>
        <button>submit</button>
    </div>
</template>
<script>
import RepositoryCard from "../../components/MyProject/RepositoryCard.vue";
export default {
    name: "MyProjectEditSelected",
    components: { RepositoryCard },
    data() {
        return {
            repositoryList: [
                {
                    name: "A",
                },
                {
                    name: "B",
                },
                {
                    name: "C",
                },
                {
                    name: "D",
                },
                {
                    name: "E",
                },
                {
                    name: "F",
                },
            ],
        };
    },
};
</script>
