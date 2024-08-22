import React from 'react';

const people = [
    {
        name: 'Shubham Chaurasiya',
        role: 'Frontend developer',
        imageUrl:
            'https://avatars.githubusercontent.com/u/91664093?v=4',
    },
    {
        name: 'Poonam',
        role: 'Team Leader',
        imageUrl:
            'https://media.licdn.com/dms/image/v2/D4D03AQEf26MfDL6R2w/profile-displayphoto-shrink_200_200/profile-displayphoto-shrink_200_200/0/1723793534754?e=2147483647&v=beta&t=-a4r02Ycx6bnQFvEPPQ72rCjTnV_DoD1j6IOO-M9sxE',
    },
    {
        name: 'Renuka',
        role: 'Backend Developer',
        imageUrl:
            'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTEKgeO5_ETZFHo4zGdBRsqCHOoAi6XJlryow&s',
    },
    {
        name: 'Vaishnavi',
        role: 'Backend Developer',
        imageUrl:
            'data:image/jpeg;base64,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',
    },
    {
        name: 'Yash',
        role: 'Frontend Developer',
        imageUrl:
            'https://media.licdn.com/dms/image/v2/D4D03AQETWWaF18ZXyA/profile-displayphoto-shrink_800_800/profile-displayphoto-shrink_800_800/0/1723882877199?e=1729123200&v=beta&t=4CBThkRqfA6SMzGcYHe8ofqBRalcZDf8L3qcHLswJvI',
    },
    {
        name: 'Siddhant',
        role: 'Frontend Developer',
        imageUrl:
            'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSnX411AzVaOOX6QDLZTdcqyZo4rlKJyA6pXQ&s',
    },
    // More people...
];

export default function LeadershipTeam() {
    return (
        <div className="bg-white py-24 sm:py-32">
            <div className="mx-auto grid max-w-7xl gap-x-8 gap-y-20 px-6 lg:px-8 xl:grid-cols-3">
                <div className="max-w-2xl">
                    <h2 className="text-3xl font-bold tracking-tight text-gray-900 sm:text-4xl">Meet Our Leadership</h2>
                    <p className="mt-6 text-lg leading-8 text-gray-600">
                        Our team is dedicated to connecting you with the best helpers for your needs. Meet the leaders who drive our mission forward.
                    </p>
                </div>
                <ul role="list" className="grid gap-x-8 gap-y-12 sm:grid-cols-2 sm:gap-y-16 xl:col-span-2">
                    {people.map((person) => (
                        <li key={person.name}>
                            <div className="flex items-center gap-x-6">
                                <img alt="" src={person.imageUrl} className="h-16 w-16 rounded-full" />
                                <div>
                                    <h3 className="text-base font-semibold leading-7 tracking-tight text-gray-900">{person.name}</h3>
                                    <p className="text-sm font-semibold leading-6 text-indigo-600">{person.role}</p>
                                </div>
                            </div>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
}