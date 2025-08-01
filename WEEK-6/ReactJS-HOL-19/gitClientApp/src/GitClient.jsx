import axios from 'axios';
class GitClient {
    static getRepositories(username) {
        const url = `https://api.github.com/users/${username}/repos`;
        return axios.get(url);
    }
}

export default GitClient;