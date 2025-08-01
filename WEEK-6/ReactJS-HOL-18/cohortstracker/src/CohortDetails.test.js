import React from 'react';
import { mount, shallow } from 'enzyme';
import CohortDetails from './CohortDetails';
import { CohortsData } from './Cohort';
import renderer from 'react-test-renderer';

describe("Cohort Details Component", () => {
    // Test 1: should create the component
    test('should create the component', () => {
        const wrapper = shallow(<CohortDetails cohort={CohortsData[0]} />);
        expect(wrapper.exists()).toBeTruthy();
    });

    // Test 2: should initialize the props
    test('should initialize the props', () => {
        const cohort = CohortsData[0];
        const wrapper = mount(<CohortDetails cohort={cohort} />);
        expect(wrapper.props().cohort).toEqual(cohort);
        expect(wrapper.props().cohort.cohortCode).toEqual('INTADMDF10');
        expect(wrapper.props().cohort.technology).toEqual('.NET FSD');
    });

    // Test 3: should display cohort code in h3
    test('should display cohort code in h3', () => {
        const cohort = CohortsData[0];
        const wrapper = mount(<CohortDetails cohort={cohort} />);
        const h3Element = wrapper.find('h3');
        
        expect(h3Element.exists()).toBeTruthy();
        expect(h3Element.text()).toContain(cohort.cohortCode);
    });

    // Test 4: should always render same html
    test('should always render same html', () => {
        const cohort = CohortsData[0];
        const tree = renderer
            .create(<CohortDetails cohort={cohort} />)
            .toJSON();
        expect(tree).toMatchSnapshot();
    });
});