import axios from 'axios';
import GitClient from './GitClient';

jest.mock('axios');

describe("Git Client Tests", () => {
    test("should return repository names for sreeshanth", async () => {
        const mockRepositories = {
            data: [
                { name: "repository1" },
                { name: "repository2" },
                { name: "repository3" }
            ]
        };

        axios.get.mockResolvedValue(mockRepositories);

        const result = await GitClient.getRepositories('sreeshanth');

        expect(axios.get).toHaveBeenCalledWith('https://api.github.com/users/Sree-45/repos');
        
        expect(result).toEqual(mockRepositories);
        expect(result.data.length).toBe(3);
        expect(result.data[0].name).toBe("repository1");
    });
});