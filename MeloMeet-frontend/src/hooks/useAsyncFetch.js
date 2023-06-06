import { useEffect, useState } from "react"

export function useAsyncFetch(url){
    const [loading, setLoading] = useState(true);
    const [result, setResult] = useState(null)

    useEffect(() => {
        const fetchData = async () => {
            try {
                setLoading(true);
                const res = await fetch(url);
                const json = await res.json();
                setResult(json);
                setLoading(false);
            } catch (e) {
                setLoading(false);
            }
        }

        fetchData();
    }, []);


    return [result, loading];
}